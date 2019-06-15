public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen
     */

    @Override
    public void betaal(double tebetalen) throws TeWeinigGeldException {
	    if (tebetalen <= kredietlimiet && tebetalen <= saldo ) {
		    saldo -= tebetalen;
	    }
	    else{
	        throw new TeWeinigGeldException("heeft te weinig geld!");
	    }
    }
}
