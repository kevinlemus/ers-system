package com.revature.project1.Models;

public class Requests {

        private int r_id;
        private String r_status;
        private int r_amount;
        private String r_type;
        private String r_requester;

        public Requests() {
        }

        public Requests(int r_id, String r_status, int r_amount, String r_type, String r_requester) {
            this.r_id = r_id;
            this.r_status = r_status;
            this.r_amount = r_amount;
            this.r_type = r_type;
            this.r_requester = r_requester;
        }



        public int getRequesterID() {
            return r_id;
        }

        public void setRequesterID(int r_id) {
            this.r_id = r_id;
        }

        public String getStatus() {
            return r_status;
        }

        public void setStatus(String r_status) {
            this.r_status = r_status;
        }

        public int getAmount() {
            return r_amount;
        }

        public void setAmount(int r_amount) {
            this.r_amount = r_amount;
        }

        public String getType() {return r_type; }

        public void setType(String r_type) { this.r_type = r_type; }

        public String getRequester() { return r_requester; }

        public void setRequester(String r_requester) { this.r_requester = r_requester; }


}
