package com.ra.model;

public interface TableForm {
    interface accounts {
        String column = " %-15s | %-15s | %-10s | %-10s | %-12s | %-15s";
    }

    interface billDetails {
        String column = " %-20s | %-10s | %-15s | %-10s | %-5s";
    }

    interface bills {
        String column = " %-10s | %-15s | %-15s | %-20s | %-30s | %-20s | %-30s | %-15s";
    }

    interface employees {
        String column = " %-15s | %-20s | %-30s | %-33s | %-15s | %-40s | %-15s";
    }

    interface products {
        String column = " %-15s | %-20s | %-15s | %-30s | %-15s | %-15s | %-15s";
    }
}