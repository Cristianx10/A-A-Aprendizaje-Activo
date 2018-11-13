package com.example.aprendizajeactivo.app_comunity;

public interface IOnBackPressed {
    /**
     * If you return true the back press will not be taken into account, otherwise the activity will act naturally
     * @return true if your processing has priority if not false
     */
    public boolean onBackPressed();
}
