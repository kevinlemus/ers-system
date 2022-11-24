package com.revature.project1.Models;

public class Requests {

        private int r_id;
        private String r_status;
        private int r_amount;
        private String r_type;
        private Employee r_requester;

        public Requests() {
        }

        public Requests(int r_id, String r_status, int r_amount, String r_type, Employee r_requester) {
            this.r_id = r_id;
            this.r_status = r_status;
            this.r_amount = r_amount;
            this.r_type = r_type;
            this.r_requester = r_requester;
        }



        public int getRequestID() { return r_id; }

        public void setRequestID(int r_id) { this.r_id = r_id;}

        public String getRequestStatus() {
            return r_status;
        }

        public void setRequestStatus(String r_status) {
            this.r_status = r_status;
        }

        public int getRequestAmount() {
            return r_amount;
        }

        public void setRequestAmount(int r_amount) {
            this.r_amount = r_amount;
        }

        public String getRequestType() {return r_type; }

        public void setRequestType(String r_type) { this.r_type = r_type; }

        public Employee getRequestRequester() { return r_requester; }

        public void setRequestRequester(Employee r_requester) { this.r_requester = r_requester; }



}
