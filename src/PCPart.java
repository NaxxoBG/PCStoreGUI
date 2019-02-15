import java.text.NumberFormat;
import java.util.Locale;

public abstract class PCPart
{
   String name;
   double value;
   NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

   public PCPart(String name, double value) {
      this.name = name;
      this.value = value;
   }

   public PCPart() {
      name = null;
      value = 0;
   }

   public abstract String getName();

   public abstract void setName(String name);

   public abstract double getValue();

   public abstract void setValue(double value);

   public String toString() {
      return this.getClass().getName() + "                                                      " + name + "                                          " + formatter.format(value);
   }
}