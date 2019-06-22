import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "factuur_regel")

public class FactuurRegel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false,nullable = false)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factuur_id")
    private Factuur factuur;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artikel_id")
    private Artikel artikel;

    public FactuurRegel(){}

    public FactuurRegel(Factuur factuur, Artikel artikel){
        this.factuur = factuur;
        this.artikel = artikel;
    }

    public String toString(){
        return artikel.toString();
    }
}
