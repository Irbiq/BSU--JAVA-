package rmi;



import dbmanager.DBManager;
import entity.Train;
import entity.TrainList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 21.03.2017.
 */
public class TrainObjectServiceDB extends UnicastRemoteObject implements TrainRemote {

    protected TrainObjectServiceDB() throws RemoteException {
    }


    private static String addTrainNumberQuery = "INSERT INTO  mydb.train_numbers (TrainNumber) VALUES (?)";
    private static String addTrainRouteQuery = "INSERT INTO mydb.train_info (idTrain,Destination,ArrivalTime,DepartureTime) " +
            "VALUES ((select id from `mydb`.`train_numbers` where TrainNumber = ?),?,?,?)";
    private static String getRangeQuery = "SELECT TrainNumber,Destination,ArrivalTime,DepartureTime FROM mydb.train_info  " +
            "INNER JOIN mydb.train_numbers ON idTrain=train_numbers.id " +
            "WHERE Destination = (?) and DepartureTime BETWEEN (?) and (?)  ";
    private static String deleteTrainByIdQuery = "delete from `mydb`.`train_numbers` where `mydb`.`train_numbers`.TrainNumber = ?";
    private static String getAllTrainsQuery = "SELECT TrainNumber,Destination,ArrivalTime,DepartureTime FROM mydb.train_info  " +
            "INNER JOIN mydb.train_numbers ON idTrain=train_numbers.id ";


    public void addTrain(Train t) {

        Connection connection;
        connection = DBManager.getInstance().getConnection();
        try {

            PreparedStatement statement = connection.prepareStatement(addTrainNumberQuery);
            statement.setInt(1, t.getNumb());
            statement.executeUpdate();
            statement = connection.prepareStatement(addTrainRouteQuery);
            statement.setInt(1, t.getNumb());
            statement.setString(2, t.getDestination());
            statement.setTime(3, t.getArrivalTime());
            statement.setTime(4, t.getDepartureTime());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Что-то пошло не так");
        }
    }

    public List<Train> range(String destination, Time from, Time to) {

        Connection connection;
        connection = DBManager.getInstance().getConnection();
        List<Train> trains = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement(getRangeQuery);
            statement.setString(1, destination);
            statement.setTime(2, from);
            statement.setTime(3, to);
            ResultSet rs = statement.executeQuery();

            trains = createTrainsList(rs);

        } catch (SQLException e) {
            System.out.println("Что-то пошло не так");
        }

        return trains;

    }


    @Override
    public void deleteTrain(Integer number) {

        Connection connection;
        connection = DBManager.getInstance().getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement(deleteTrainByIdQuery);
            statement.setInt(1, number);
            statement.executeUpdate();

        } catch (SQLException e) {
            //System.out.println("Что-то пошло не так");
            e.printStackTrace();
        }
    }


    public List<Train> getAllTrains() {

        Connection connection;
        connection = DBManager.getInstance().getConnection();
        List<Train> trains = new ArrayList<>();
        try {

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getAllTrainsQuery);
            trains = createTrainsList(rs);

        } catch (SQLException e) {
            System.out.println("Что-то пошло не так");
        }

        return trains;

    }

    @Override
    public List<Train> getAllTrains(String file) throws RemoteException {
        return null;
    }

    @Override
    public void saveTrains(String file, TrainList tl) throws RemoteException {

    }

    private static List<Train> createTrainsList(ResultSet rs) throws SQLException {

        ArrayList<Train> trains = new ArrayList<>();
        Train t;
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            t = new Train();
            t.setNumb(rs.getInt(1));
            t.setDestination(rs.getString(2));
            t.setArrivalTime(rs.getTime(3));
            t.setDepartureTime(rs.getTime(4));
            trains.add(t);
        }
        return trains;
    }


}
