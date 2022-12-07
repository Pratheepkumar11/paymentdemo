package com.example.paymentdemo.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;


import com.example.paymentdemo.dto.Wallet;
import com.example.paymentdemo.exception.AlreadyExistingAccountNumberException;

import com.example.paymentdemo.exception.InsufficeintAmountException;
import com.example.paymentdemo.exception.WalletException;
import com.example.paymentdemo.exception.passwordexp;

public class WalletDaoImpl implements WalletDao {






	public Wallet addWallet(Wallet newWallet) throws WalletException, InsufficeintAmountException, AlreadyExistingAccountNumberException {

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment","root","");
			 PreparedStatement pst = con.prepareStatement("insert into acc values(?,?,?,?)");
			 PreparedStatement preparedStatement = con.prepareStatement("select COUNT(uid) FROM acc where uid =?")) {
			pst.setInt(1, newWallet.getId());
			preparedStatement.setInt(1, newWallet.getId());
			if (newWallet.getId() > 0) {
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.next();
				if (resultSet.getInt(1) == 0) {
					pst.setString(2, newWallet.getName());
					pst.setDouble(3, newWallet.getBalance());
					pst.setString(4, newWallet.getPassword());
					if (newWallet.getBalance() > 1000) {
						boolean rowUpdated = pst.executeUpdate() > 0;
						if (rowUpdated) {
							//System.out.println("Account Created Successfully");

						}
					} else {
						//throw new InsufficeintAmountException(super.toString());
						throw new InsufficeintAmountException("Insufficient Amount");
					}
				} else {

					//throw new AlreadyExistingAccountNumberException(super.toString());
					throw new AlreadyExistingAccountNumberException("Already exists");
				}
			} else {
				//throw new WalletException(super.toString());
				throw new WalletException("Invalid account Number");
			}

		} catch ( SQLException ia) {
			//throw new WalletException("Invalid account Number");
			ia.printStackTrace();
		}
		return newWallet;

	}

	public Wallet getWalletById(Integer walletId) throws WalletException {
		// TODO Auto-generated method stub
		Wallet updatedWallet = null;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment","root","");
			 PreparedStatement pst = con.prepareStatement("SELECT * FROM acc WHERE uid=?")){

			pst.setInt(1, walletId);
			ResultSet resultSet = pst.executeQuery();
			resultSet.next();
			int check = resultSet.getInt("uid");
			if (check != 0) {
				updatedWallet = new Wallet(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),resultSet.getString(4));

			} else {
				throw new SQLException();

			}
		}
		catch (SQLException ia) {

			throw new WalletException("Invalid account Number");
		}



		return updatedWallet;
	}

	public Wallet updateWallet(Wallet updateWallet) throws WalletException {
		// TODO Auto-generated method stub


		Wallet newUpdatedWallet ;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment","root","");
			 PreparedStatement pst = con.prepareStatement("update acc set ubalance=? where uid=?");
			 PreparedStatement pstt = con.prepareStatement("SELECT * FROM acc WHERE uid=?"))
		    {

			pst.setDouble(1,updateWallet.getBalance());
			pst.setInt(2,updateWallet.getId());
			pst.executeUpdate();
			System.out.println("Account updated");
			pstt.setInt(1,updateWallet.getId());
			ResultSet resultSet = pstt.executeQuery();
			resultSet.next();

			newUpdatedWallet = new Wallet(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3),resultSet.getString(4));


		} catch (  SQLException ia) {
			throw new WalletException("Invalid account Number");
		}

		//System.out.println(newUpdatedWallet);

		return newUpdatedWallet;
	}

	public Wallet deleteWalletById(Integer walletID) throws WalletException {


		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "");
			 PreparedStatement pst = con.prepareStatement("DELETE FROM `acc` WHERE uid = ?")) {

			pst.setInt(1,walletID);
			pst.execute();
			System.out.println("data deleted");


		} catch (SQLException ia) {
			throw new WalletException("Invalid account Number");
		}

		return null;
	}

}
