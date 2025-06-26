package models;

public class ClasseTarifDispo {
    private Classe classe;
    private Param_vol paramVol;
    private int restePlace;
    
    public Classe getClasse() {
        return classe;
    }
    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    public Param_vol getParamVol() {
        return paramVol;
    }
    public void setParamVol(Param_vol paramVol) {
        this.paramVol = paramVol;
    }
    public int getRestePlace() {
        return restePlace;
    }
    public void setRestePlace(int restePlace) {
        this.restePlace = restePlace;
    }
}

