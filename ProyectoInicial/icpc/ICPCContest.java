package icpc;
/**
 * This class can simulate the all problem
 * 
 * @author Daniel Rojas
 * @author Sebastian Zamora
 * @version 1.0
 */
public class ICPCContest
{
    int max = 0;
    public int solve(int cost,int [][]routesSpeedLimit){
        int answer = solucion(cost,routesSpeedLimit);
        return answer;
    }
    
    public int solucion(int cost,int [][]routesSpeedLimit){
        int answer = 0;
        for (int i = 0; i<routesSpeedLimit.length;i++){
            if(routesSpeedLimit[i][2] > max){
                max = routesSpeedLimit[i][2];
            }
        }
        if (cost < max){
            for (int i = 0; i<routesSpeedLimit.length;i++){
                int routeExtense = max - routesSpeedLimit[i][2];
                if(routeExtense > cost || routeExtense == 0){
                    answer += cost;
                }
                else{
                    answer += routeExtense;
                }
            }
        }
        else{
            for (int i = 0; i<routesSpeedLimit.length;i++){
                answer+= max - routesSpeedLimit[i][2];
            }
        }
        return answer;
    }
    
    /***
     * let me know th cost
     */
    
    public int simulate(int cost,int[][]routesSpeedLimit){
        int answer = solucion(cost,routesSpeedLimit);
        for (int i = 0; i<routesSpeedLimit.length;i++){
            if((max-routesSpeedLimit[i][2])<cost && (routesSpeedLimit[i][2]) != max){
                 routesSpeedLimit[i][2] = 0;
            }
        }
        ICPC simulator = new ICPC(cost,routesSpeedLimit);
        simulator.makeVisible();
        return answer;
    }
    
    public boolean ok(){
        return true;
    }
}
