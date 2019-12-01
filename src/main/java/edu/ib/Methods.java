package edu.ib;

public class Methods {

    private Function f;

    public Methods(Function f) {
        this.f = f;
    }

    public double bisection(double a, double b, double precisionAWanted) {
        int i = 0;
        double xrOld = 0;
        double xrNew = 0;
        double precisionA = 1;
        while (precisionA > precisionAWanted) {

//            if (f.f(a) * f.f(b) >= 0) {
//                System.out.println("Wrong values given.");
//                break;
//            }
            i++;

            xrNew = (a + b) / 2;
            precisionA = Math.abs((xrNew - xrOld) / xrNew) * 100;
            // double Et = Math.abs(((trueValue - xrNew) / trueValue) * 100);

            if (f.f(xrNew) == 0) {
                // System.out.println("Iteration: " + i + "; xl=" + a + "; xu=" + b + "; xr=" + xrNew + "; precision=" + precisionA + "%");
            } else if (f.f(xrNew) != 0) {
                // System.out.println("Iteration: " + i + "; xl=" + a + "; xu=" + b + "; xr=" + xrNew + "; precision=" + precisionA + "%" + "; Et=");// + Et + "%"
                if ((f.f(xrNew) * f.f(a)) < 0) {
                    b = xrNew;
                }
                if ((f.f(xrNew) * f.f(b)) < 0) {
                    a = xrNew;
                }
            }
            xrOld = xrNew;

        }
        return xrNew;
    }

    public double fixedPoint(double x0, double precisionAWanted) {
        int i = 0;
        double precisionA = 1;
        double x_n = 0;
        while (precisionA > precisionAWanted) {
            i++;
            x_n = f.f(x0) + x0;
            precisionA = Math.abs((x_n - x0) / x_n) * 100;
            // double Et = Math.abs(((trueValue - x_n) / trueValue) * 100);
            //  System.out.println("Iteration: " + i + "; xn=" + x_n + "; precision=" + precisionA + "%" + "; Et=" + Et + "%");
            x0 = x_n;
        }
        return x_n;
    }

    public double newtonRaphson(double x_i, double precisionAWanted) {
        int i = 0;
        double x0 = x_i;
        double precisionA = 1;
        while (precisionA > precisionAWanted) {
            i++;
            x_i = x0 - ((f.f(x0) / (-1 * Math.exp(-x0) - 1)));
            precisionA = Math.abs((x_i - x0) / x_i) * 100;
            //double Et = Math.abs(((trueValue - x_i) / trueValue) * 100);
            //System.out.println("Iteration: " + i + "; xi=" + x_i + "; precision=" + precisionA + "%" + "; Et=" + Et + "%");
            x0 = x_i;
        }
        return x_i;
    }

    public double secant(double x_i, double x_prev, double precisionAWanted) {
        int i = 0;
        double precisionA = 1;
        double x_n =0;
        while (precisionA > precisionAWanted) {
            i++;
            x_n = x_i - (f.f(x_i) * (x_prev - x_i) / (f.f(x_prev) - f.f(x_i)));
            precisionA = Math.abs((x_n - x_prev) / x_n) * 100;
           // double Et = Math.abs(((trueValue - x_n) / trueValue) * 100);

            //System.out.println("Iteration: " + i + "; xn=" + x_n + "; precision=" + precisionA + "%" + "; Et=" + Et + "%");
            x_i = x_prev;
            x_prev = x_n;
        }
        return x_n;
    }
}

