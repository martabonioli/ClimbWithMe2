package android.example.climbwithme;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyModel {
    private static final MyModel ourInstance = new MyModel();
    private static MyModel instance = null;



    public static Utente utente;
    public static Uscita cercaUscita = new Uscita("",0.0,0.0,0.0,0.0,"","","","","","",0);

    public static double getLatposition() {
        return latposition;
    }

    public static void setLatposition(double latposition) {
        MyModel.latposition = latposition;
    }

    public static double getLongposition() {
        return longposition;
    }

    public static void setLongposition(double longposition) {
        MyModel.longposition = longposition;
    }
    public static Utente getUtente() {
        return utente;
    }

    public static void setUtente(Utente utente) {
        MyModel.utente = utente;
    }

    public static double latposition;
    public static double longposition;
    private static int i = 0;



    //Creo arrayList per le uscite
    private static ArrayList<Uscita> uscite = null;

    private MyModel() {
        //Istanza delle uscite
        uscite = new ArrayList<Uscita>();
    }


    public static void popola(List<Uscita> uscit){
        uscite = new ArrayList<>(uscit);
        Log.d("Uscite nel metodo popola", uscite.size()+"");
    }

    public ArrayList<Uscita> getUscite(){
        return uscite;
    }

    public Uscita get(int index) {
        return uscite.get(index);
    }

    public static int getSize() {
        return uscite.size();
    }


    public static synchronized MyModel getInstance() {
        if (instance == null) {
            instance = new MyModel();
        }
        return instance;

    }

    public  static void setSessionId(String sessionId) {
        utente = new Utente(sessionId,"iVBORw0KGgoAAAANSUhEUgAAATAAAAEwCAYAAAAw+y3zAAAAAXNSR0IArs4c6QAAAARnQU1BAACx" +
                "jwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAhdEVYdENyZWF0aW9uIFRpbWUAMjAyMDowMjoy" +
                "MCAxMToyODowNI/rkrMAACdFSURBVHhe7Z0JlFTFFYbLREJAFlmGQWDYVCIIAiIoRhZBk2hQIQE3" +
                "CAZcQoxBjkbUBEHBFZEtIQTHkLCIkaOIGJYQQWDYhZEdBhlh2Hdk2BSSGP5L37FoevZ+r9/yf+e8" +
                "U91FzzD9XtVf996qunXR119//Y0hhBAf8p1ISQghvoMCRgjxLRQwQohvoYARQnwLg/ikWPzvf/+T" +
                "8ptvzjUjvNfXuXHRRReZ73zn3NiJ10DfE1IYKGCkQKhQ/fe//xWBgvCUKFHCfPe735WrOOB34jpz" +
                "5kzO79bfSWEjeUEBI7kC0frPf/4jInLxxReb733ve5F/OR98bseOHebAgQNyHTx40Jw8edKcPn3a" +
                "nG1f8pmSJUvKz19yySWmcuXKJikpSa6UlJQcKywa/N/4Hfj9+P8pZiQaChg5D7WGIDbRgnXq1Cmz" +
                "bNkys3r1arNq1Sqzfv16k5mZaQ4fPhz5RNGAkF1++eWmYcOGpkmTJqZx48bmuuuuM9///vcjnzgH" +
                "xBBWWjysPhIMKGAkx9KClQS3UMnOzjbTp0838+fPN2lpaWbDhg2Rf8kdWFN6AbWa1AWFi6hXftSt" +
                "W9e0a9fO/OQnP5EL1psCIYOg0TILNxSwEAMRgNCULl06UmNMVlaWmTBhgvnggw9Menp6pPZ8IBr4" +
                "OYhStBgVRJiAChxQwYMQ4echprG4/vrrzZ133mm6du1qatWqFak15quvvhKr0RZfEg4oYCEDooML" +
                "7iGECBw/fty8+eabZuzYseIW2kBU4K5BIKLFyklU1PT/VgtOadq0qenevbvp0aOHKV++vNRB+GCV" +
                "4WdolYUDClhIUOFCXEk794IFC8yoUaPM5MmT5b0CAYB4qGh5AbXQcMFytOnYsaN55plnxEID+J6w" +
                "yvTzJLhQwEIALBNbuN555x3z6quvmjVr1sh7ANFSC8sropUbapnh74TIKtdcc4154YUXRNAA/g0W" +
                "mVqaJHhQwAIMOjA6us4mQrieffZZiXMp6NxesrQKi4qZHTerU6eOefnll829994r7yFisMrwORIs" +
                "KGABBJ0VglSqVCl5P2PGDPPb3/7WfPHFF/IewBrD54JE9HeqX7++GTFihLn11lvlPZaBqCtKggEF" +
                "LGDAEtFZxU2bNplf//rXZt68efIeBFG4oon+ju3btzepqalimQEssqVbGQw4FAUEdFhcKl6///3v" +
                "xQJR8VKrI+jiBfQ76neeM2eOrCl7/vnn5T3ukd4v4m9ogQUAxLCwCBUdFivl77nnnpw4F+I+dqA7" +
                "jNj3AFbY1KlTJeCPOmxVYmzMv9AC8zlYUoBYF8QLFsYNN9wg4oX3uhQi7OAeaOxr69atslXppZde" +
                "EuHCvYtelkH8Ay0wHwMXCMsjDh06ZNq2bWvWrVsn9eiodI9iY98biP2sWbNkIayuGyP+gk/Mh2gH" +
                "hHghxlWtWjURL1gZuCheuYN7o/dp6dKlpkaNGubTTz+Ve4mZW947f0EB8xlwhzQ1zdChQ83NN98s" +
                "cRxYD+iAfl3P5SZ6n3DPsI2qRYsWZsyYMXJfMTtJEfMPFDAfAfFCzAbWw29+8xvz5JNPSj1dxqKB" +
                "e6ZuY69evUzfvn1FwGCNMXboDxgD8wkqXuC2226T2A2AmNHqKh72PezSpUvO3lAsfOUMpbehgPkA" +
                "W7xatWplFi5cSKvLAVTIkHts5syZUkcR8zYUMI9jixeylK5cuZLi5SB6b1u3bi2JHAFFzLswBuZh" +
                "bPFq2bIlxcsFNC6GVEO6hxLPgPfcm1DAPAo6jIoX0ipjyp/i5Q4qYh9//HFOah7M+vLeew8KmAdB" +
                "R9HNxkif/Mknn1C8XAb3GjGxDz/8UDbE4/7zGXgPCpgHQceBgPXv399MmjRJ3rPjJI6//OUvsuYO" +
                "VhieBfEODOJ7DI17vf/++6Zz585Sp7NjxH3se4+lKz/+8Y+ZjsdDUMA8hObyQuJBnJMI6LYkHvsZ" +
                "7N271yQnJ1PEPAJdSI8Ay0tzed14441SUry8AZ6BLqPA8grAmUlvQAHzCBArcP/995t9+/ZRvDwG" +
                "BhiI2ObNm2UbF2Nh3oAupAfQuBcS7XXq1EnqGPfyHvYzmT17tqwToyuZWChgCUbFCx1Bj86n9eVd" +
                "9NngmZ04cUJEjSv1EwddyASjDf++++6TEu8pXt4FzwYiBtHq2bOn1Kn7T9yHFlgC0VlHTM8jwwTx" +
                "B7YriR0SOBGcrmRioIAlCIzkyDsFKlWqZA4fPkzX0Ufos8IhIVj2glAAcuvTGnMX3u0EoSP4K6+8" +
                "QvHyIepK4pAQnDlJ1z8x0AJLABq4RxC4TJkyUkcB8x/6zOA6nu1H8p4BfXehBZZAcPgs4OjtT9QK" +
                "Qyxz0KBBOXXEPWiBuYxaX0eOHDEVK1aUOq758i+25QwrDBu+aYW5By0wl1Gh6tevn5Ro6BQv/wLx" +
                "UrF6+eWXpaQV5h60wFxErS+M1DoDydiX/9FniGPZcEAu4EG57sA77CIQMDBs2DApGfsKBniGECsM" +
                "TDhfEiAuRpyHFphL6AiNeBfXfQUPfZa1atUy27Ztk8FKByziHLTAXAKNGeKFTcAQL7ymeAUHfZZZ" +
                "WVlm+fLlYl1jYStxFgqYS2igd/jw4VJy20nwKFGihJQjR46UEoMUcRa6kC6gwfvjx4+bsmXLSh2X" +
                "TgQP+5lqyWC+s/DOuoDGQv7xj39IyaUTwQTPVK2uKVOmSMlgvrNQwFxAXYvx48dLSdciuGhoYNy4" +
                "cVJq6IA4A11Ih1H3EauzNec93cfgYs8s0410Ht5Vh1H3ccaMGVKiIVO8gos9s4wDiYG2ARJ/KGAO" +
                "oyPvtGnTpKRLEXw0ZIBTvQEHLOegC+kgGI2xuRciVqVKFXPgwAG6jyFA3cgf/OAHZtOmTRLIty0z" +
                "Ej8oYA6CRos9jzgmrWrVqlJHAQs+9jNGqmmNgdL6jj90IR1Ep9A1FsL4V/hIS0uTkhaYM1DAXGDx" +
                "4sVScgQOBxikNA62cOFCKTlwOQMFzCHQYBH/AqtWrZKSo3B40Ge9evVqKTl4OQMFzCHsUTg9PV1K" +
                "jsLhQZ+1Pnu0BQ5g8YcC5iAI5iLzBA7vABSw8KDPeufOnbIOjJv3nYEC5hC6eHHLli1SkvCCcyMB" +
                "B7D4QwFzGG28nIEMF3jWsMCBtgG6kPGHAuYQ2lgzMzOlZBA3fOguDFpgzkEBcwgdfffu3SslCR86" +
                "aO3evVtKEn8oYA6hjRfbh0g4UYvr0KFDUpL4QwFzCLXAVMAY/wgf+syxlQwwjBB/KGAOoQJ27Ngx" +
                "KUl40TagbYLEDwqYA2Dk1cZ6+vRpKUl4YRtwDgqYw3z99ddScgYqvOhp3ST+UMAcQi0wHupAdFEz" +
                "Xcj4QwFzCLW4dEM3CS/aBmiFxx8KmMNo4+XoG144iDkHBcwB7G1DJUuWlJKEF2TlJc5AAXMIXQNU" +
                "oUIFKUn40K1ElSpVkpKnE8UfCphDqIAlJydLqY2ZhI+kpCQpGQOLP+xVDqGNtXLlylIyBhZe1AKj" +
                "gMUfCpjDpKSkSMnGGz7UCq9Zs6aUHMTiDwXMIbSx1q1bV0quBwsXeP4a89I2wL2Q8YcC5hAa89LG" +
                "CwuMI3A40TZA4g8PtnUIuA+YPsc+OF1KAQGjKxkO7GetJbYUcTInvvBuOgjcRixirF69urynBRYe" +
                "9Fk3atRISgxkFK/4wzvqEGisZ86ckddNmjSRkgIWHjTe1bRpUym1LZD4QgFzEA3iNmvWTEqOwOFB" +
                "ZyBVwBg6cAb2KAdRi6tNmzZSYhSmFRYOdPBq27atlJyBdAYG8R1EA/nICab74SBgHI2Djf2MGcB3" +
                "Ft5RB0GD1VnIBg0a5NSRYKPWVqtWraTEAEbL2xnYmxxG0wl36NBBSgpY8FGr6/bbb5eSoQPnYG9y" +
                "GG24HTt2lJKzUcFH41/6zClezsEYmMNoHAxgTRgEDFaYzlKRYKHPtmLFinIeJF5zDZhz8K46DBru" +
                "yZMn5XWXLl2k5IxUcNFn261bNykZvHcW3lkX0JjIAw88ICXdyOCiz7Z79+5ScsbZWehCuoDtRpYq" +
                "VSpnVKYbGSz0mSKB4f79+0W8MANJC8w5eGddwHYjH3roISnpRgYPDdY/+uijUp44cYLi5TC0wFwC" +
                "M1Owvj7//HNTr169SC0JChAvdRf37Nljqlatak6dOsWBymE4PLgEGjLiI1deeaW59tprpe7iiy+W" +
                "kvgfFar27duLeCFMQPFyHgqYi+ii1gEDBkjJLK3BQZ+lPltdC0achS6ky5QoUULcDYzS+/btYzA/" +
                "AMCShoAhNJCRkSGv+UzdgRaYyyCwCwYOHCgl8T9qfb344otSwn0k7kALzGXsJRVYrX3kyBFaYT5G" +
                "nx1OHsrKypLXDA24By0wl0GDVytsyJAhUhL/ogPPyJEjpaT15S60wBKExsJq1Khhdu3aRSvMh2CW" +
                "EcH6+vXrmw0bNshrBu/dhRZYgsAaITB+/HgpueXEX2DwUbEaN26clFh1T9yFApYgMHOFBt+uXTtJ" +
                "OQ0B47oh/wCLGfz85z83zZs3l2fJdX3uQxcygWAEx+p8rNyuVq2a1Nkruok3sd39o0ePmnLlynHV" +
                "fYKgBZZA0ODR8C+77DIzePDgSC3xOjrAvPXWWxSvBEMLLMFgJEeiQ4zqCAZv2rQpJzhMvIc+mxYt" +
                "Wphly5bJkglc6lISd6GAeQB1JXfs2CHriQBdSe9hu46HDx82FSpUkCwjjH0lDg4bHgCjOjpCSkqK" +
                "SU1NjdSeEzHiDewB5b333qN4eQQKmEfA6I5sFcgXhpktdBa6Jd5BBaxnz57yfDjr6A3oQnoIuCe6" +
                "zahu3bpm69atjId5AHUdGzZsaNauXStCBgHjAJN4KGAeAwHh0qVLy+p8rNIHduyFuIvee+ycOHjw" +
                "IGcdPQaHEI8BtwQdpHr16mbhwoVShw7EeJj74J7rwLF8+XIRL+xjpXh5BwqYB0EHwabgH/7wh2bi" +
                "xIlSB7eFIuYedtB+2rRppkmTJhK0hyVGvAMFzKPAdUGcpWvXrmbYsGFSRxFzB1u8sFj1jjvuYNDe" +
                "o1DAPAw6EtJQ9+nTx7zwwgtSRxFzFlu83njjDfPggw+KePGeexMKmMdBDAaB/f79+5vnnntO6ihi" +
                "zgCrV8XrtddeM0888YQsbeG99i6chfQBEDG4L7iQBPGpp56Ses5Oxg/7Xo4ePdr06tUr5xAW4l0o" +
                "YD4BnQudDPsmEZd5+OGHpZ7rxIqPfQ8nTZpk7rvvPoqXT6CA+Qy4OCVLljRz5swxt9xyi9RRxIqO" +
                "fe/S0tLMTTfdxJiXj6CA+RBYY1ixj1O+W7dubfbu3Sv1dgCa5I19r7AHdfHixbJwGMtXYOkSf8An" +
                "5UPQwbAmCad879y509x6661Sjw7Jzpc/sLpUvO666y6zfft2ES/cU94/f8Gn5VMQ0NdV4bNnz845" +
                "ZxLWGepIbCBQ6jIOHTrUTJ06VV4zs4Q/oQvpc9AZEdiHaK1evdp06NBBrDJgx3fCjn0vateubWbM" +
                "mCEJJLFMAstUKPr+hBaYz9GOidhN48aNJSli79695d9Qj1hPmAPS+v1VvH73u99Jlg+IF/acwpWk" +
                "ePkXWmABQjNZgJUrV8o2pIyMDHkP1wmdVWM/QUeFS9d2YS8jjrBr1KiRvKfLGAxogQUIdEhYYria" +
                "NWsm+fWxZuySSy6RjqzWRpAtMnw33Ad8V3znsmXLmrFjx5rPPvtMxAtWF+4PxSsYUMACBiwtXLAw" +
                "4DZhLx/yt+vqfdSpkAVpxg3fBd8J3w2WKPjDH/4gx5716NFDvjfuSdC+d9ihCxlgYIGg48ICA+jM" +
                "6NSjRo2S9wAWCzq1CpufyO1vxx7GAQMGSP4uoLO1FK7gQQELAdFCBktk+PDhsmE5Oztb6gByXakY" +
                "eFXMIFoqXJhBVC699FLTt29fydyBE54AvidEi8IVXChgIULFSQP94N133xWLDNtobCBmED6NnSUS" +
                "FSz8HfgONtiJANHq1KlTpOacxaUuJQk2FLAQosJkC1lWVpYZN26cmTx5slm/fn2k9ltsQQNOiRrE" +
                "CqjlZFtZyhVXXCGnAz3wwAOmWrVqkVpjjh8/LsF5WlzhgQIWYiBGCHhjISwuBXssp0yZYmbNmmXm" +
                "zZsXqT0fCI3O5OH3qKAVVNhUqFCq4OBvye3nYWlhke7dd99tatWqFak1svFaF6JSuMIHBYwIcM1w" +
                "IdOFnfcdSw7gXs6fP1/KpUuXOp5qBhvVW7Zsadq0aSMXMkTYyx5glUG4IFp0E8MNBYxcgIoZhAyC" +
                "Fg2yX2Ch7MaNG80XX3xhtmzZYrZt2ybHjh05ciTyqbzBydbJycliTcElrFOnjpy7eO2115qkpKTI" +
                "p74FggXhomgRGwoYyRO4hxAzuHawgiBo6v7lBqw2BNIhOHiNz8NFhWWFuFssUbTB/wkrD64hfpbu" +
                "IckNChgpFBrv0lIFRkUGV34Cpz+v4qgLT/Fz+vMULFIQKGAkLkCMbHILxkeLG4WKFAcKGCHEt3D4" +
                "I4T4FgoYIcS3UMAIIb6FAkYI8S0UMEKIb6GAEUJ8CwWMEOJbKGCEEN9CASOE+BYKGCHEt1DACCG+" +
                "hXshSbGJ3shdWLihmxQVChiJiS1KdvocRVPe6AXs14pmn4jOToHfp/+Hvs7t/7AzWET/fhJuKGAh" +
                "RgUkWqCQuBD5vVBGp79JFPjbkDdMs8XifbTAUdzCBwUsJKhYaedHZ4dAIW10XiJ17NgxSRe9c+dO" +
                "s2fPHrNv376cCwflIoU0LmRgxTmMuJCFNS9wbiMunFOJC2c6IsV0+fLlTeXKlc1ll11mqlatKleN" +
                "GjUk7TT+LS80gyu+J76Ppp2mqAUbClhAUYsKgqViZZ88ZIMc96tXrzabNm2SE4kyMjKkxFFrXqJm" +
                "zZrm8ssvN1dddZWpV6+eufLKK03jxo1F5GIBQUNaa9wDCJpabCQ4UMACgi1Y6KzIOx/dWWGlpKen" +
                "mxUrVphVq1aZTz/91Kxbty7HOssL7fz279Sfw/+r2K/zwrb69HX079bvlB/4+auvvloOBMF13XXX" +
                "mWbNmkkO/mhOnTpFQQsQFDAfgw4OKwOdEJ01ujPixKA5c+aIYC1atCjmgbUKOjOsNKDCURRhijfR" +
                "QmeLDr57Xn9XSkqKadWqlVxt27YVy80G3/Ns+xdBw3enmPkPCpjPQGfDFevIs8zMTDN9+nQzd+5c" +
                "ES6cVB0LWB/orPg9KgCJEqjiogKHEt8L3wPCFgvE3dq3by9idtttt5kGDRpE/uUcsFBx4ffgIt6H" +
                "AuYDVLQgWPahsxCoGTNmmH/+859yxTqTMUhiVRjUWsP3h6WF7x9NuXLlzE9/+lMRszvvvPO8iQL7" +
                "8Fzb6iPeggLmUXITre3bt5vJkyfL0f9LliyJ1H6LWg4QKb3It5YahAgXBCqa5s2bm44dO5q7775b" +
                "DttV8BwQO8O91ftLvAEFzENoTAvxGDsAvXXrVvP3v/9dhAszhTbojOhUamFRsAqGbaHh3uHe20DA" +
                "7rnnHtO1a1dTv379SO23biZjZt6AAuYBVHxwarWCpQ3jx483EydONGvXro3UngOdDp1Pf44UH3UT" +
                "Y1lndevWNT179jS//OUvTfXq1SO1Rta8AZ38IO5DAUsQGpeBe2ivz4KVNXbsWPOvf/0rUnMOfI5W" +
                "ljvY1lm0mF1//fXmscceM/fff3+OBQaLDJ/D52mVuQsFzGXUTSxTpkyk5pyL+MYbb5jU1FTpDAo6" +
                "hAoWRSsx5CZmqINF1qdPH3PNNddEas9ZZfp54jwUMJeI5SYiEP/666+bpUuXRmrOdQw0frqH3iO3" +
                "Z4Pg/xNPPGHuvffeSA2FzC0oYA6Dxg6wBglg6cPw4cPNH//4R7N//36pA3AR81uYSbwDxAmxL9sq" +
                "K1u2rAjZk08+Ka8BZi8BhcwZKGAOAeFCI9fZxF27dplBgwaZMWPGyHtAaysYQMiinyGC/s8995yp" +
                "Xbu2vD/bzyR8QCGLLxSwOIOGjECurpLfvHmzefbZZ8VdVNCI0dijp+6Jv8Fzx6CkVjfo1KmTeeml" +
                "l3KWYlDI4gsFLE5oo1VXEfsOIVwfffSRvAd0E8NBLPeyQ4cO5rXXXsvZvkTXMj5QwIqJLodAXiuA" +
                "GUXEQD744AN5D2K5GCT4aIjA3puJLUtDhw6VtEAAQobPcflF0aCAFZFo4UJAHgHct99+W94DWlwE" +
                "xBKyX/ziF2bIkCGmSpUq8h4JIfEZClnh4N0qAmiIECeIF4SsX79+Jjk5OUe80BDRaOFCULwI2gDa" +
                "jAoZmDBhgrSZZ555Rt6jLemARwoOLbBCoEF3nVnEivnevXvL6AnoKpKCEG2RYcnFiBEjTI8ePeQ9" +
                "UnKjDanYkdyhgBUQWFPqLmLh6a9+9SuzZs0aeQ+zHw2OwkUKA4QMlw6MDRs2NH/7298koyzAwAir" +
                "jOQOXch8gEUFgYJ4oUF169bNtGzZMke8tAFSvEhhQZtB21EhQ3pvrOpHG8NhKmhzaHtogyQ2tMBy" +
                "QUVJl0WMGzfO9OrVK+fEHbiLjFeQeGK3KVhef/3rXyXYDzQfGTkfClgMMOLpoRg4SuxnP/tZzn5F" +
                "NCKOiMRJ7DZ24403mkmTJsnRchA3hDIoZN9CFzIKNBJYXRAvZIioVq1ajnjBzKd4EadBG0NbA4sX" +
                "L5btSNg7CwsNbZOW/7fQAouARgOzHY0EyQSRKx1HkAFaXSRR2G0P8TEskEZSRaRdQn3YrTFaYGeB" +
                "WY6RDeKFzdY4GRripcFVihdJFGh72g5xjicO8UV6cSTBpDUWcgvMXteF/E3YeDt79mypo9VFvIbd" +
                "Ju+44w7J3ou2qxNLCHuEjdAKGBoCRjE0innz5pm77rrLZGdny79htOOyCOJF7LaJY+BmzZplbrjh" +
                "BmnPcCvD5lKG0oVUlxEPe8CAAebmm28W8YILCShexKto24S1dfToUVmTOHDgQGnLaNNo22EidBYY" +
                "3EaY3ciMigNNFy5cKPVoEOpSEuIH7DaLE8dnzpwpE1EIh+hgHHRCI2B40DC/sb5r5cqVYnVhtTPd" +
                "ReJn7PZbuXJlCYdcffXVkjgR9UGPi4XChUR8AMKFC6ubsdcM4oWHS/EifsZ2KQ8ePCj7KZHpAm0d" +
                "1ljQJ6ICL2C6MBUj1SOPPGIeeughqafLSIIE2rIG8Lt37y656TQuFuSlFoF2IfHgcIwZRiG4jGlp" +
                "aVJPt5EEFbtt33LLLebf//63vA5qXCywAgbRwuiD04BwmjJKChcJC+phIHX1smXLTKVKlQIpYoF0" +
                "ISFSEC+spsc+MogX410kTOikVWZmpklJSTEbNmzI8UaCRKAEDA8NIoUAJk4DatasmbiRiAUw3kXC" +
                "BvoCBm6k4sHMJNxJDOxBGsgDI2AQKDwsiFdqaqqc/gJQF/SZGEJyQ/sF+NGPfpQzQwmCMKgHQsDw" +
                "IDBljK1BgwcPltlGgAdHy4uEHVvEMEOJ/PvoK4iH+b1/+F7AYF1BvOAmPv3003IB+P8UL0LOYYtY" +
                "nz59ZPsRBMzvIubrWUjceIwkeDCPPfaYGTVqlNRztpGQ2Nh9A4P9q6++KnFiXCpwfsK3AmaLF04I" +
                "evPNNyP/QgjJC1vEsOAVmYf9KmK+dCFt8Xr44YcpXoQUAts7GTp0qIiYX91J3wkYbjBiXhCvRx99" +
                "1Lz11luRfyGEFAZYYmDYsGHmqaee8qWI+UrAcGNxgxGwRyBy9OjRkX8hhBQWWGIqYkOGDDH9+/eX" +
                "/gXjwC8i5isBw43FDcaNxlQwIaR42CI2aNAgETKEZ7TO6/gmiI8bjQV4I0eONI8//rjU2cFIQkjR" +
                "sfvS2LFjTY8ePSSnmNeFzBcCphuzJ06cmHNSMcWLkPhi96lp06bJwSFePxHc8wIGXxwpoOfOnStp" +
                "cwHFixBnsPsWsli0aNHC01ksPC1ganlt2rTJ1K9fX+r8FGAkxI/YfWzHjh1yFqVXRcyzAoZFdUj/" +
                "gcM3qlSpkmPKcmM2Ic6jfQ19b/fu3fLei+6kJ2chof4QL4Az73DjMCpQvAhxB/Q19Ln9+/dLHwQI" +
                "5XjN+/GcgOEGqamKw2bXr19Pt5GQBIA+h763YsUK061bN4mPea0vek7AcJMgYM8//7zMhOA9xYuQ" +
                "xIC+hz749ttvy7YjrBHzEp6KgWncCwd03n777VJnz4oQQtzH7oOffPKJadu2rWfiYZ4RMBUvzHrU" +
                "rFlT6ihehHgDdR3RJw8cOOCZQ0I84ULixmjQvnXr1lLihlG8CPEG6KOwuNAn27RpI3VY4pTo8E7C" +
                "BUxvDOjatavZtm0bg/aEeBCdmcTEGnLwwRrDlUgSLmBQdKTHwf6rSZMm5dQRQrwH+iZECzn4pkyZ" +
                "IvuTEf5JFAmNgWnca/v27aZWrVpSx7gXId7G9pCwTiwpKSlh8bCEWWC4AfChAY5AB+pjE0K8ix32" +
                "0f3JsMQSEfZJqAsJa6t3797m888/l9dcaU+IP0BfRZ9du3at6devX8KWVCTEhVTXcdGiReamm26S" +
                "OrqOhPgLu8+mp6ebpk2buu5Kui5gUG51HStUqGC+/PJLzjoS4lO072LT9759+6Tuq6++kno3cN2F" +
                "hGoDJCakeBHib9B30YcRzMchO27jqgWmriO2I7Rr107q6DoS4m/sPrxkyRLJXuGWK+magEGpkY4D" +
                "XHrppebo0aO0vggJCNqXq1atavbs2SN1briSrrmQqtC9evWieBESMNSV3Lt3r+nbt6/UueFZuWKB" +
                "qeu4bt0606hRI6mj60hIsLD7NJZGXXHFFY67ko5bYFBmzSHUpUsXKblglZDggT6t68E6d+4sJfq+" +
                "k33dcQHDHw8F/tOf/iSHc0CluWCVkGCiC1xXr15txo0bJ33fyb2SjrqQuuYLyc80XQ5jX4QEG+3j" +
                "sL7gQsIqcyoBoitBfF0fgi9A8SIk2GhA//Tp0zmn6DuFYxaYWl8bN240DRo0kDoG7gkJB3Zf37p1" +
                "q6ldu7YjVphjFpjOPPTs2VNK/OEUL0LCAfq6itWDDz4opROzkY4IGIJ2SFL48ccfm6VLl0odA/eE" +
                "hAvt83PnzjVpaWmiCfEO6DviQkJ5cdWpU4cpogkJMdr369WrZzIyMkTU4mnMxN0CQ+AO4jVx4kQR" +
                "L0DxIiScaN/fvHmzef/990Ub4mmFxd0C00WrSDN78OBBWl+EhBzVgGrVqpldu3ZJfOys7kh9cYmr" +
                "BXbmzBkpx4wZQ/EihAiqAbt375bFrZihjJcVFjcLDH+kZpsoX768yc7OpoARQgTVAk18CCtMDZ7i" +
                "EDcLTBUVxy1RvAghNtACWF5IfDh+/Hh5jXh5cYmbBaaxr4oVK5ojR45QwAgh56GakJycLGl38Lq4" +
                "rmRcLDBV0gkTJlC8CCExUSsMLuR7770nOlFcKywuFhhW2OKPweG0OKSWAkYIiYVqA3KFIWdYca2w" +
                "YltgCMThj5o1a5aIF6B4EUJiodqwZcsWWaEP7ShOMD8uFhjiX82bNzcrVqxwPP8PIcTfqEa0atXK" +
                "LFiwoFhuZLEEDH8E8nytX7/eNGzYMFJLCCEFQ1NPFzVTRVyC+K+88oqUTuw2J4QEDxWrF198Ucqi" +
                "ZqopsgWm+b5OnDhhypQpI3V2DiBCCMkNWytw/FrJkiWLdAxbkS0w3VH+5z//WUooKsWLEFIQoBVq" +
                "hWHrIShK7LzIFpgunahevbrscdLpUUIIKQiqGVh+hcw1RUm1UyQLTJdOYAYB4gUoXoSQwqCakZWV" +
                "ZRYvXiwWWWGXVBRJwOC/ghEjRkjJ4D0hpCggSyvAsYtAtaWgFNqFhGoi68TZn8vJPsHgPSGkKNja" +
                "AesLxlBhgvmFtsA00IYd5QD/EcWLEFIU7GC+akphgvmFFjDNOjF69GgpC6qUhBCSF6mpqVKqW1kQ" +
                "CuVC6tqvnTt3mpSUFKmj+0gIKQ62hiBfGNLRF3RlfqHMJ53iRNocwLVfhJDiYruRSDkNCrqcolAW" +
                "GNxFBNmuuuoqOSIJr4uy+IwQQmxUS7Cneu3atRLQL4hxVGABU/cRx4TXrVtX6ug+EkLiga0lOLkI" +
                "JxgVxI0ssAupJt27774rJd1HQki8sN3Id955R8qCuJEFFjBstgT6y6GYhBASL1RTVGMKMhtZIBdS" +
                "3Uec9YgZAkD3kRAST2xNOXr0qClXrly+bmSBLDA15aZMmSIl3UdCSLyx3cgPP/xQyvzcyAIJmP7S" +
                "jz76SEouXiWEOIFqy9SpU6XMy/oC+bqQuvcR6LYhlMw+QQiJN+pGaoJDkNfeyHxNKTXh0tLSctxG" +
                "ihchxAlUY84aVmblypXyWutika+A6Q/PnDlTysLsUyKEkMKiGjN9+nQp84qD5Stgunkb5z6C/IJq" +
                "hBBSHNTDmzFjhpQQtNy8vjxjYBArLJ/AVCaOTwNcPkEIcRJbY6BBiH/lFgfL0wJT1Zs/f76UhBDi" +
                "JosWLZIyN6MpXxcSzJs3T0qYcrS+CCFOAo3R5RNz5syRMrfQVa4CButLg2lz586VkvEvQoib5Gc8" +
                "5RoDg4DZOe+1pAVGCHEaXWsK4Tp9+rTUxYqD5WqBqVCtWrVKSkIIcQvVH+QF27x5s7yOZTzlKmDq" +
                "LuK8NsD9j4QQt4DWaBwMi+hBrBBWrgKmptqSJUukjDWFSQghTqGas3z5ciljEVOV4HvqYbUrVqyQ" +
                "kgF8Qoib6DKu9PR0KXVRvU3MID4D+ISQRGNrjpbRgfyYFph+eN26dVISQkgiwVkcINqIiilg6i5+" +
                "9tlnUkLxaH0RQtwEmqPW1po1a6RUt1KJKWCKWmD5JRUjhBAnUO1RASuQBaY/pAIWrXqEEOImOCsS" +
                "RBtTFwiYPQO5YcMGKek+EkISgYazNm7cKCUEzDaoLpiF5AwkIcQrqPZgCQWytOI1So2NXWCBqVDt" +
                "3LlTSkIISTTYD4mj1tSoUmK6kED3HxFCiBfIyMiQ0l5Un6sFlpmZKSV8TrqPhJBEAO1Rq0vXgtlc" +
                "IGBKVlaWlPaqV0IIcRudVNy+fbuUdhD/AnVStdMPE0KIF9i2bZuUdhzsAgHTdRax1I4QQtxGNUg1" +
                "yV4Ldp6A4YP6j7t27ZKS8S9CiBfYu3evlHZc/gILTGNe+/fvl5ICRghJJKpBKmC6Nkxe2wtZYYFx" +
                "ESshxEuoBml+fLzWxaznWWAqVF9++aWUhBDiFZAfHxqVZxAfHDp0KPKKEEK8gxpXamzFFDBaYIQQ" +
                "L3L48OHIq3OcJ2A6XXnkyBEpgSodIYQkGlubQEwLLDs7W0quwieEJBrbiDp+/LiUamydp1D6wRMn" +
                "TkhJCCFeQAP3J0+elFKJaWLph+xoPyGEJArVomjjKqaAnTp1Skq6kIQQL6ACpi5knrOQWCQGGMAn" +
                "hHgJrAWzOW8lPla5lilTxgwePNg8/fTTpnTp0lJHV5IQkihgSCGlDg61HTNmjHnkkUfEEpOTuiFg" +
                "emVnZ5/97DffPP744xA1Xrx48fLUNXDgQNGoY8eOiWadZ4EhVWupUqXMggULzLRp00xSUtJ56VsJ" +
                "ISQRIAMFdgh17tzZtGjRQuL0qLvgVCIE7jUDIiGEeA3dE2mMMf8HxJ2w+lbb3o0AAAAASUVORK5C" +
                "YII=","","","", 0,0,0,"");

    }

    public static String getSessionId() {
        return utente.getCodiceSessione();
    }





    public static List<Uscita> deserialize(JSONObject serverResponse) {
        List<Uscita> list = new ArrayList<>();
        //uscite = new ArrayList<Uscita>();
        try {
            JSONArray usciteJSON = serverResponse.getJSONArray("uscite");
            Log.d("usciteJSON", String.valueOf(usciteJSON));
            for (int i = 0; i < usciteJSON.length(); i++) {
                JSONObject uscitaJSON = usciteJSON.getJSONObject(i);
                Uscita uscita = new Uscita (uscitaJSON);
                list.add(uscita);
                //uscite.add(uscita);
            }
            Log.d("Uscite nel metodo deserialize", ""+uscite.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //return uscite;
        return list;
    }

    public void insertUtente (JSONObject response) {
        try {
            JSONArray utentiJSON = response.getJSONArray("utente");
            Log.d("jsonObj", String.valueOf(utentiJSON));
            JSONObject utenteJSON = utentiJSON.getJSONObject(0);
            Log.d("jsonObj", String.valueOf(utenteJSON));
            Utente u = new Utente(utenteJSON);
            MyModel.utente.setCodiceSessione(u.getCodiceSessione());
            MyModel.utente.setNome(u.getNome());
            MyModel.utente.setMinLiv(u.getMinLiv());
            MyModel.utente.setMaxLiv(u.getMaxLiv());
            MyModel.utente.setCognome(u.getCognome());
            MyModel.utente.setNumeroTelefono(u.getNumeroTelefono());
            MyModel.utente.setDataDiNascita(u.getDataDiNascita());
            MyModel.utente.setFoto(u.getFoto());
            MyModel.utente.setLivelloMaxLead(u.getLivelloMaxLead());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
