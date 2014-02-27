package by.euanpa.gbs.models.gson;

/**
 * Created by google on 18.02.14.
 */
public class Bind {

    private int id;
    private int busStopId;
    private int routeId;
    private int nextBusStopId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusStopId() {
        return busStopId;
    }

    public void setBusStopId(int busStopId) {
        this.busStopId = busStopId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getNextBusStopId() {
        return nextBusStopId;
    }

    public void setNextBusStopId(int nextBusStopId) {
        this.nextBusStopId = nextBusStopId;
    }
}
