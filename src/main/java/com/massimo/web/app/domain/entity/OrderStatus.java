package com.massimo.web.app.domain.entity;

public enum OrderStatus {
    POR_ATENDER("Por atender"),
    ATENDIDO("Atendido"),
    PAGADO("Pagado");

    String state;

    OrderStatus(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
