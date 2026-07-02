package org.internship.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;

    private double amount;

    @Enumerated(EnumType.STRING)
    private FineStatus status = FineStatus.UNPAID;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "police_id")
    private Police police;

    public Fine() {}

    public Fine(String reason, double amount, Vehicle vehicle, Police police) {
        this.reason = reason;
        this.amount = amount;
        this.vehicle = vehicle;
        this.police = police;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public FineStatus getStatus() { return status; }
    public void setStatus(FineStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public Police getPolice() { return police; }
    public void setPolice(Police police) { this.police = police; }

    @Override
    public String toString() {
        return "Fine{id=" + id + ", reason='" + reason + "', amount=" + amount +
                ", status=" + status + ", plate=" + (vehicle != null ? vehicle.getPlateNumber() : "N/A") + "}";
    }
}