package model;

public class Film {

    private Integer id;
    private String titolo;
    private String genere;
    private Integer anno;

    public Film(final String titolo, final String genere, final Integer anno) {
        this.titolo = titolo;
        this.genere = genere;
        this.anno = anno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }
}