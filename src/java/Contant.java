public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    @Override
    public boolean betaal(double tebetalen) {
	    if (tebetalen <= saldo) {
		    saldo -= tebetalen;
		    return true;
	    }
	    else{ return false; }
    }
}
