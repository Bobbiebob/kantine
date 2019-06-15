public class Pinpas extends Betaalwijze {

    private double kredietlimiet = 0;
    private boolean heeftLimiet = false;

    /**
     * Methode om kredietlimiet te zetten
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
        this.heeftLimiet = true;
    }

    /**
     * Methode om betaling af te handelen
     */

    @Override
    public void betaal(double tebetalen) throws TeWeinigGeldException {
	    if (kredietlimiet >= tebetalen || !heeftLimiet && tebetalen <= saldo) {
	        saldo-=tebetalen;
	    }
	    else{
	        throw new TeWeinigGeldException("heeft te weinig geld!");
	    }
    }
}
