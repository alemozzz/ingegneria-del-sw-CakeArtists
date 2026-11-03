package entity;

public class EntityRider {
    private int idRider;
    private String email;

    public EntityRider() {}

    public EntityRider(int idRider, String email) {
        this.idRider = idRider;
        this.email = email;
    }

    public int getIdRider() { return idRider; }
    public void setIdRider(int idRider) { this.idRider = idRider; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}