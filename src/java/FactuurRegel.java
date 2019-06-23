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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artikel_id")
    private Artikel artikel;

    private ReadDB readDB = new ReadDB();

    public FactuurRegel(){}

    public FactuurRegel(Factuur factuur, Artikel artikel){
        this.factuur = factuur;
        this.artikel = artikel;
    }

    @Override
    public String toString(){
        //return artikel.toString();
        return readDB.getArtikel(factuurRegelId);
    }
}
