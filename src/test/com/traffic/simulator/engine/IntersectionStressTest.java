package com.traffic.simulator.engine;

import com.traffic.simulator.entity.VehicleType;
import com.traffic.simulator.entity.vehicle.PriorityVehicle;
import com.traffic.simulator.entity.vehicle.Vehicle;
import com.traffic.simulator.exception.TrafficJamException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionStressTest {

    private Intersection intersection;
    private boolean isGreenLight = true; // Biến cờ để điều khiển đèn giao thông thủ công



    // 1. Giả lập Đèn giao thông
    class FakeTrafficLight extends TrafficLight {
        @Override
        public boolean isGreen() {
            return isGreenLight;
        }
    }

    // 2. Giả lập Xe thường
    class FakeVehicle extends Vehicle {
        public FakeVehicle(String id) {
            super(id, 10.0, 1, VehicleType.CAR);
        }
        @Override public void move() {} // Không cần logic move trong lúc test ngã tư
    }

    // 3. Giả lập Xe ưu tiên
    class FakeAmbulance extends PriorityVehicle {
        public FakeAmbulance(String id, Intersection in) {
            super(id, 20.0, VehicleType.AMBULANCE, in, 100);
        }
        @Override public void activateSiren() {}
    }



    @BeforeEach
    void setUp() {
        FakeTrafficLight fakeTrafficLight = new FakeTrafficLight();
        // Dùng luôn số cứng (2 slot, 50 queue) để tránh lỗi do đọc file Config
        intersection = new Intersection(fakeTrafficLight, 2, 50);
    }

    @Test
    @DisplayName("Stress Test: 100 xe cùng tiến vào ngã tư khi đèn xanh")
    void test100VehiclesPassSimultaneouslyWithoutCollision() throws InterruptedException {
        int totalVehicles = 100;
        ExecutorService executor = Executors.newFixedThreadPool(totalVehicles);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(totalVehicles);
        AtomicInteger successCount = new AtomicInteger(0);

        isGreenLight = true; // Bật đèn xanh

        for (int i = 0; i < totalVehicles; i++) {
            final int id = i;
            Vehicle v = new FakeVehicle("Car-" + id);

            executor.submit(() -> {
                try {
                    startLatch.await(); // Chờ lệnh xuất phát
                    intersection.enter(v);
                    Thread.sleep(2); // Thời gian xe đi qua ngã tư
                    intersection.exit(v);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    System.err.println("Lỗi tại xe " + id + ": " + e.getMessage());
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        startLatch.countDown(); // Phát lệnh cho 100 luồng cùng chạy
        boolean finished = doneLatch.await(10, TimeUnit.SECONDS); // Chờ tối đa 10s
        executor.shutdown();

        assertTrue(finished, "Lỗi: Hệ thống bị treo (Deadlock) hoặc chạy quá chậm!");
        assertEquals(100, successCount.get(), "Lỗi: Số xe đi qua không đủ 100!");
    }

    @Test
    @DisplayName("Test: Ném lỗi TrafficJamException khi hàng đợi quá tải")
    void testTrafficJamExceptionThrownWhenQueueFull() {
        isGreenLight = false;

        for (int i = 0; i < 50; i++) {
            Vehicle v = new FakeVehicle("Wait-" + i);
            assertDoesNotThrow(() -> intersection.enter(v));
        }

        // Chiếc xe thứ 51 đi vào chắc chắn phải ném ra lỗi Kẹt xe
        Vehicle extraVehicle = new FakeVehicle("Over-1");
        assertThrows(TrafficJamException.class, () -> intersection.enter(extraVehicle));
    }

    @Test
    @DisplayName("Test: Xe ưu tiên không bị chặn bởi đèn đỏ")
    void testPriorityVehicleBypassesRedLight() {
        isGreenLight = false; // Dù đèn đang đỏ

        FakeAmbulance ambulance = new FakeAmbulance("AMB-01", intersection);

        // Xe cứu thương vẫn đi lọt mà không ném lỗi gì
        assertDoesNotThrow(() -> {
            intersection.enter(ambulance);
            intersection.exit(ambulance);
        });
    }
}