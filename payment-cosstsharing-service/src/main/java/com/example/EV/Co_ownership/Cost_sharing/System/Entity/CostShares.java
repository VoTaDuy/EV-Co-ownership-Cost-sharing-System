    package com.example.EV.Co_ownership.Cost_sharing.System.Entity;


    import com.example.EV.Co_ownership.Cost_sharing.System.Enum.CostShareStatus;
    import jakarta.persistence.*;

    import java.math.BigDecimal;

    @Entity (name = "cost_shares")
    public class CostShares {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int share_id;

        @Column(name = "user_id")
        private int user_id;

        @Column(name = "share_percentage")
        private BigDecimal share_percentage;

        @Column(name = "amount_due")
        private BigDecimal amount_due;

        @Column(name = "settled_amount")
        private BigDecimal settled_amount;

        @Column(name = "status")
        private CostShareStatus  status;

        @ManyToOne
        @JoinColumn(name = "cost_id", referencedColumnName = "cost_id")
        private VehicleCost cost_id;

        public int getShare_id() {
            return share_id;
        }

        public void setShare_id(int share_id) {
            this.share_id = share_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public BigDecimal getShare_percentage() {
            return share_percentage;
        }

        public void setShare_percentage(BigDecimal share_percentage) {
            this.share_percentage = share_percentage;
        }

        public BigDecimal getAmount_due() {
            return amount_due;
        }

        public void setAmount_due(BigDecimal amount_due) {
            this.amount_due = amount_due;
        }

        public BigDecimal getSettled_amount() {
            return settled_amount;
        }

        public void setSettled_amount(BigDecimal settled_amount) {
            this.settled_amount = settled_amount;
        }

        public CostShareStatus getStatus() {
            return status;
        }

        public void setStatus(CostShareStatus status) {
            this.status = status;
        }

        public VehicleCost getCost_id() {
            return cost_id;
        }

        public void setCost_id(VehicleCost cost_id) {
            this.cost_id = cost_id;
        }
    }
