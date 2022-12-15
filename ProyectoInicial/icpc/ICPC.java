package icpc;
import shapes.Canvas;
import java.util.*;

/**
 * Let me create and modify an ICPC.
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 3.0
 */
public class ICPC
{
    private List<String> Colors = List.of("yellow","blue","red","orange","green","magenta","cyan","pink","gray","lightGray","darkGray","black");
    private HashMap<String, Intersection> intersections = new HashMap<>();
    private HashMap<String, Route> routes = new HashMap<>();
    private HashMap<String,Sign> signs = new HashMap<>();
    private int  cost = 0;
    private boolean isVisible = false;
    private int totalCost = 0;
    private boolean confirm = true;
    private String exepcion;
    private int minimo = 1000000;
    private String type = "Normal";
    /**
     * Constructor for objects of class ICPC
     * @param int length, int widht
     */
    public ICPC(int length, int widht)
    {
        Canvas board = Canvas.getCanvas("ICPC", widht, length);
        board.redraw1();
    }

    /**
      * Constructor for objects of class ICPC knowing the cost
     * @param int length, int widht , int cost
     */
    public ICPC (int length, int widht,int cost){
        new ICPC(length,widht);
        this.cost = cost;
    }

    /**
      * Constructor for objects of class ICPC knowing the cost
     * @param int cost
     * @ int[][] routesSpeedLimit
     */
    public ICPC (int cost,int [][]routesSpeedLimit){
        new ICPC(1000,1000);
        this.cost = cost;
        try{
            for(int[] i: routesSpeedLimit){
                Random num = new Random();
                addIntersection(Colors.get(i[0]-1),num.nextInt(650),num.nextInt(650));
                addIntersection(Colors.get(i[1]-1),num.nextInt(650),num.nextInt(650));
                routeSpeedLimit(Colors.get(i[0]-1),Colors.get(i[1]-1),i[2]);
            }
            makeVisible();
        }
        catch(ICPCException e){
            exepcion = e.getMessage();
            confirm = false;
        }
    }

    /**
     * create new intersections
     * @param the name of the color
     * @param the xposition of the intersection 
     * @param the yposition of the intersection
     */
    public void addIntersection(String color, int x, int y) throws ICPCException
    {
        makeInvisible();
        confirm = true;
        try{
            if(!intersections.containsKey(color)){
                intersections.put(color, new NormalIntersection(color, x, y));
            }
            else{
                throw new ICPCException(ICPCException.IntersectionExist);
            }
        }
        catch(ICPCException e){
            exepcion = e.getMessage();
            confirm = false;
        }
        makeVisible();
    }

    /**
     * create new intersections
     * @param the name of the color
     * @param the xposition of the intersection 
     * @param the yposition of the intersection
     */
    public void addIntersection(String type,String color, int x, int y) throws ICPCException{
        makeInvisible();
        try{
            if(!intersections.containsKey(color)){
                if(type == "Needy"){
                    intersections.put(color, new Needy(type,color, x, y));
                }
                else if(type == "Hermit"){
                    intersections.put(color, new Hermit(type,color, x, y));
                }
                else if(type == "Normal"){
                    intersections.put(color, new NormalIntersection(color, x, y));
                }
            }
            else{
                throw new ICPCException(ICPCException.IntersectionExist);
            }
        }
        catch(ICPCException e){
            exepcion = e.getMessage();
            confirm = false;
        }
        makeVisible();
    }

    /**
     * deleate intersections
     * @param  String color
     * @return void
     */
    public void delIntersection(String color)throws ICPCException{
        makeInvisible();
        confirm = true;
        try{
            if(intersections.containsKey(color)){
                for(String i: routes.keySet()){
                    String[] values = i.split(",");
                    if(color == values[0] || color == values[1]){
                        delRoad(values[0],values[1]);
                    }
                }
                intersections.remove(color);
            }
            else{
                throw new ICPCException(ICPCException.IntersectionNotExist);
            }
        }
        catch(ICPCException e){
            exepcion = e.getMessage();
            confirm = false;
        }
        makeVisible();
    }

    /**
     * create new routes
     * @param the color of the intersection A
     * @param the color of the intersection B
     * @return void
     */
    public void addRoute(String interA, String interB) throws ICPCException{
        makeInvisible();
        if(intersections.containsKey(interA) && intersections.containsKey(interB)){
            String nRoute = interA+","+interB;
            String nRouteR = interB+","+interA;
            if(!(routes.containsKey(nRoute)||routes.containsKey(nRouteR))){
                Intersection intersecA = intersections.get(interA);
                Intersection intersecB = intersections.get(interB);
                try{
                    intersecA.addRoute(intersecB,type);
                    routes.put(nRoute,intersecA.getRoutes().get(nRoute));
                }
                catch(ICPCException e){
                    exepcion = e.getMessage();
                    confirm = false;
                }
            }
        }
        makeVisible();
    }

    /**
     * create new routes
     * @param the color of the intersection A
     * @param the color of the intersection B
     * @return void
     */
    public void addRoute(String type,String interA, String interB){
        confirm = true;
        try{
            if(type == "Fixed"){
                this.type=type;
                addRoute(interA,interB);
                this.type = "Normal";
                }
            else if(type == "Rebel"){
                this.type=type;
                addRoute(interA,interB);
                this.type = "Normal";
                }
            else if(type == "Normal"){
                addRoute(interA,interB);
                }
        }
        catch(ICPCException e){
            exepcion = e.getMessage();
            confirm = false;
        }
    }

    /**
     * This let me create a route with a speed limit
     * @param the initial point of the route
     * @param the final point of the route
     * @param the route's speed Limit
     */
    public void routeSpeedLimit(String intersectionA, String intersectionB, int speedLimit){
        confirm = true;
        try{
            addRoute(intersectionA,intersectionB);
            routes.get(intersectionA+","+intersectionB).setSpeedLimit(speedLimit);
            putSign(intersectionA,intersectionB,speedLimit);
        }
        catch(ICPCException e){
            exepcion = e.getMessage();
            confirm = false;
        }
    }

    /**
     * deleate routes
     * @param  String locationA, String locationB
     * @return void
     */
    public void delRoad(String locationA, String locationB){
        makeInvisible();
        if(intersections.containsKey(locationA) && intersections.containsKey(locationB)){
            String nRoute = validateRoute(locationA, locationB);
            if(routes.containsKey(nRoute)){
                try{
                    routes.get(nRoute).getIntersections().get(locationA).removeRoute(nRoute);
                    routes.get(nRoute).getIntersections().get(locationB).removeRoute(nRoute);
                    routes.remove(nRoute);
                }
                catch(ICPCException e){
                    exepcion = e.getMessage();
                    confirm = false;
                }
            }
        }
        makeVisible();
    }

    /**
     * create new Signs
     * @param the initial point of the route
     * @param the final point of the route
     * @param the route's speed Limit
     */
    public void putSign(String interA, String interB, int speedLimit) throws ICPCException{
        makeInvisible();
        try{
            plotSign(interA,interB,speedLimit,this.type);
        }
        catch(ICPCException e){
            exepcion = e.getMessage();
            confirm = false;
        }
        makeVisible();
    }

    public void putSign(String type,String intersectionA, String intersectionB, int speedLimit) throws ICPCException{
        try{
            if(type == "Cautious"){
                this.type = type;
                putSign(intersectionA,intersectionB,minimo);
                this.type = "Normal";
            }
            else if(type == "Twin"){
                this.type=type;
                putSign(intersectionA,intersectionB,speedLimit);
                this.type = "Normal";
            }
            if(type == "Normal"){
                putSign(intersectionA,intersectionB,speedLimit);
            }
        }
        catch(ICPCException e){
            exepcion = e.getMessage();
            confirm = false;
        }
    }

    private void plotSign(String interA,String interB,int speedLimit,String type) throws ICPCException{
        try{
            if(intersections.containsKey(interA) && intersections.containsKey(interB)){
                String nRoute = validateRoute(interA,interB);
                if(routes.containsKey(nRoute)){
                    routes.get(nRoute).putSign(interA,interB,speedLimit,this.type);
                    signs.put(nRoute+","+String.valueOf(speedLimit),routes.get(nRoute).getSign().get(interA+","+interB+","+String.valueOf(speedLimit)));
                }
            }
            if(speedLimit < minimo){
                minimo = speedLimit;
            }
        }
        catch(ICPCException e){
            exepcion = e.getMessage();
            confirm = false;
        }
    }

    /**
     * remove signs
     * @param the initial point of the route
     * @param the final point of the route
     */
    public void removeSign(String interA, String interB) throws ICPCException{
        makeInvisible();
        try{
            remove(interA,interB);
        }
        catch(ICPCException e){
            exepcion = e.getMessage();
            confirm = false;
        }
        makeVisible();
    }

    private void remove(String interA,String interB) throws ICPCException{
        try{
            if(intersections.containsKey(interA) && intersections.containsKey(interB)){
                String nRoute = validateRoute(interA,interB);
                if(routes.containsKey(nRoute)){
                    routes.get(nRoute).removeSign();
                }
            }
        }
        catch(ICPCException e){
            exepcion = e.getMessage();
            confirm = false;
        }
    }

    private String validateRoute(String intA,String intB){
        String route = "";
        if(routes.containsKey(intA+","+intB)){
            route = intA+","+intB;
        }
        else if(routes.containsKey(intB+","+intA)){
            route = intB+","+intA;
        }
        return route;
    }

    /**
     * Know the intersections in ICPC
     * @return String[]
     */
    public String[] intersection(){
        String[] inters = new String[intersections.size()];
        int count = 0;
        for(String i: intersections.keySet()){
            inters[count] = i;
            count ++;
        }
        Arrays.sort(inters);
        return inters;
    }

    /**
     * Know the routes in ICPC
     * @return String[][]
     */
    public String[][] routes(){
        String[][] routeStrings = new String[routes.size()][2];
        int count = 0;
        for(String j: routes.keySet()){
            routeStrings[count] = j.split(",");
            count++;
        }
        return routeStrings;
    }

    /**    
     * Know the routes sings in ICPC
     * @return String[][]
     */
    public String[][] signs(){
        String[][] señales = new String[routes.size()][3];
        int count = 0;
        for(String i: routes.keySet()){
            for(String j: routes.get(i).getSign().keySet()){
                señales[count] = j.split(",");
            }
            count ++;
        }
        return señales;
    }

    
    /** make all the diagram visible
     * @param ()
     * @return void
     */
    public void makeVisible(){
        isVisible = true;
        for(Intersection i: intersections.values()){
            if(i != null)i.makeVisibleInt();
        }
        for(Route j: routes.values()){
            if(j != null)j.makeVisibleRoute();
        }
        for(Sign k: signs.values()){
            if( k != null)k.makeVisibleSign();
        }
    }

    /**
     * make all the diagram invisible
     * @param ()
     * @return void
     */
    public void makeInvisible(){
        isVisible = false;
        for(Intersection i: intersections.values()){
            i.makeInvisibleInt();
        }
        for(Route j: routes.values()){
            j.makeInvisibleRoute();
        }
        for(Sign k: signs.values()){
            k.makeInvisibleSign();
        }
    }
    

    public String[][] wrongSings(){
        isWrong();
        String[][] wrong = new String[signs.size()][3];
        int count = 0;
        for(String i: signs.keySet()){
            String[] key = i.split(",");
            Sign signal = signs.get(i);
            if (signal.getType() == "Wrong"){
                wrong[count] = key;
                count++;
            }
        }
        return wrong;
    }

    private void isWrong(){
    for(String i: routes.keySet()){
        for(Sign j: routes.get(i).getSign().values())
            if (j.getLimit() == routes.get(i).getLimit()){
                j.isUseful("Wrong");
            }
        }
    }

    public String[][] unNecesarySings(){
        isNecesary();
        String[][] unNecesary = new String[signs.size()][3];
        int count = 0;
        for(String i: signs.keySet()){
            String[] key = i.split(",");
            Sign signal = signs.get(i);
            if (signal.getType() == "UnNecesary"){
                unNecesary[count] = key;
                count++;
            }
        }
        return unNecesary;
    }

    private void isNecesary(){
        for(String i: routes.keySet()){
            for(String j: routes.keySet()){
                String sign = routes.get(i).belongingSigns();
                String sign2 = routes.get(j).belongingSigns();
                String signal = routes.get(i).belongingSigns()+","+String.valueOf(routes.get(i).getLimit());
                String signal2 = routes.get(j).belongingSigns()+","+String.valueOf(routes.get(j).getLimit());
                    if(routes.get(i).getLimit() == routes.get(j).getLimit() &&  sign != sign2){
                    if(routes.get(j).getSign().get(signal2).getType() != "UnNecesary"){
                        routes.get(j).getSign().get(signal2).isUseful("UnNecesary");}
                }
                else if(routes.get(i).getSign().get(signal).getLimit() == routes.get(j).getSign().get(signal2).getLimit() && sign != sign2){
                    if(routes.get(j).getSign().get(signal2).getType() != "UnNecesary"){
                        routes.get(j).getSign().get(signal2).isUseful("UnNecesary");}
                }
            }
        }
    }

    /**
     * Show the best cost of ICPC
     * @return the total Cost
     */
    public int totalSingsCost(){
        String[][] señales = signs();
        totalCost = señales.length * cost;
        return totalCost;
    }
    

    /**
     * Check if the methot was complit satisfactory
     * @return a boolean confirm or deny the  methot was complit satisfactory
     */
    public boolean ok(){
        if (exepcion != "ok"){
            System.out.println(exepcion);
            exepcion = "ok";
        }
        return confirm;
    }

    /**
     * Finish the simulator
     */
    public void finish(){
        System.exit(0);
    }
}