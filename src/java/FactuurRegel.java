import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "factuur_regel")

public class FactuurRegel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factuurRegelId", unique = true, updatable = false, nullable = false)
    private long factuurRegelId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factuur_id")
    private Factuur factuur;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "artikel_id")
//    private Artikel artikel;
    @Column(name = "artikelnaam")
    private String artikelNaam;

    @Column (name = "nummer")
    private long nummer;

    public FactuurRegel(){}

    public FactuurRegel(Factuur factuur, Artikel artikel){
        this.factuur = factuur;
        this.artikelNaam = artikel.getName();
    }

    public String toString(FactuurRegel factuurRegel){
        ReadDB readDB = new ReadDB();
        //return artikel.toString();
        nummer = (factuurRegelId);
        return readDB.getArtikel(factuurRegel);
    }
}
