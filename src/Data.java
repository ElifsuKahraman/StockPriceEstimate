public class Data {
    Double price;
    String date;

    public Data(Double price,String date) {
            this.price=price;
            this.date=date;
        }
        public String toString(){
            System.out.println("Tarih: " + date + "\n" + "Fiyat: " + price );
            return "";
        }
}
