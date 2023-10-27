package ba.unsa.etf.rpr.t1;

public class Sat {
    private int hours;
    private int minutes;
    private int seconds;

    public Sat(int hours, int minutes, int seconds){
        this.set(hours, minutes, seconds);
    }

    public void set(int hours, int minutes, int seconds){
        this.hours=hours;
        this.minutes=minutes;
        this.seconds=seconds;
    }

    public void next(){
        this.seconds++;
        if(this.seconds==60){
            this.seconds=0;
            this.minutes++;
        }
        if(this.minutes==60){
            this.minutes=0;
            this.hours++;
        }
        if(this.hours==24){
            this.hours=0;
        }}

        public void previous() {
            this.seconds--;
            if(this.seconds==-1){
                this.seconds=59;
                this.minutes--;
            }
            if(this.minutes==-1){
                this.minutes=59;
                this.minutes--;
            }
            if(this.hours==-1){
                this.hours=23;
            }
        }

        public void move (int seconds){
            if(seconds>0){
                for(int i=0; i<seconds; i++){
                    this.next();
                }
            }else{
                for(int i=0; i<-seconds; i++){
                    this.previous();
                }
            }
        }

        public int getSate() {
            return this.hours;
        }

        public int getMinutes() {
            return this.minutes;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public String toString() {
            return this.hours+":"+this.minutes+":"+this.seconds;
        }

        public static void main(String[] args) {
            Sat c=new Sat(15, 30, 45);
            System.out.println(c);
            c.next();
            System.out.println(c);
            c.move(-48);
            System.out.println(c);
            c.set(0, 0, 0);
            System.out.println(c);
        }



    }



