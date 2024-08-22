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
        double weightedMultiplication =0;
        double weightedTotal =0;
        for(double i=1;i<=10;i++){
            weight.add(i);
        }
        for(int i=0;i<fileRead.priceArray.size();i++){
            double price =fileRead.priceArray.get(i);
            double weight1 =weight.get(i);
            weightedMultiplication += price * weight1;
            weightedTotal += weight1;
        }
        double wma= weightedMultiplication / weightedTotal;
        return wma;
    }

    public double CalculateEma(){
        double period=fileRead.priceArray.size();;
        double multiplier =2.0/(period+1);;

        double firstEmaValue =CalculateSma();
        double ema= firstEmaValue;

//        for(double i=0; i<fileRead.priceArray.size();i++) {
            double closingPrice = fileRead.priceArray.get(fileRead.priceArray.size()-1);
            ema = ((closingPrice - ema) * multiplier) + ema;
            return ema;
   }
}

