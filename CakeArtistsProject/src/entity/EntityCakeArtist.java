package entity;

public class EntityCakeArtist {
    private int idCakeArtist;
    private String indirizzo;
    private String email;

    public EntityCakeArtist() {}

    public EntityCakeArtist(int idCakeArtist, String indirizzo, String email) {
        this.idCakeArtist = idCakeArtist;
        this.indirizzo = indirizzo;
        this.email = email;
    }

    public int getIdCakeArtist() { return idCakeArtist; }
    public void setIdCakeArtist(int idCakeArtist) { this.idCakeArtist = idCakeArtist; }

    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}