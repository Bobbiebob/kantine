public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    @Override
    public void betaal(double tebetalen) throws TeWeinigGeldException {
	    if (tebetalen <= saldo) {
		    saldo -= tebetalen;
	    }
	    else{
	    	throw new TeWeinigGeldException("heeft te weinig geld!");
	    }
    }
}
