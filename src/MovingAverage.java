import java.util.ArrayList;

public class MovingAverage {
   FileRead fileRead;
   Double sma;

    public MovingAverage(FileRead fileRead){
        this.fileRead=fileRead;
    }
    public double CalculateSma() {
        double total = 0;
        for (Double p : fileRead.priceArray) {
            total += p;
        }
        sma=total / fileRead.priceArray.size();
        return sma;
    }

    public double CalculateWma(){
        ArrayList<Double> weight=new ArrayList<>();
        for(double i=1;i<=10;i++){
            weight.add(i);
        }
        double agirliklicarpim=0;
        double agirliklitoplam=0;
        for(int i=0;i<fileRead.priceArray.size();i++){
            double price =fileRead.priceArray.get(i);
            double weight1 =weight.get(i);
            agirliklicarpim+= price * weight1;
            agirliklitoplam+= weight1;
        }
        double wma=agirliklicarpim/agirliklitoplam;
        return wma;
    }

    public double CalculateEma(){
        double period=fileRead.priceArray.size();;
        double carpan=2.0/(period+1);;


        double ilkemadegeri=CalculateSma();
        double ema=ilkemadegeri;

        for(double i=period; i<fileRead.priceArray.size();i++){
            double kapanisfiyat= fileRead.priceArray.get((int) i);
            ema=(kapanisfiyat*carpan)+(ema*(1-carpan));
        }
        return ema;
    }

}

