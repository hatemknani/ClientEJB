package un.bank;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import un.bank.entities.Account;

public class ClientRemote {

	public static void main(String[] args) {
		try {
			Context ctx = new InitialContext();
			String appName = "";
			String moduleName = "BankEJB";
			String beanName = "BK";
			//String remoteInterface = "un.bank.BankRemote";
			String remoteInterface = BankRemote.class.getName();
			String objectName = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + remoteInterface;
			BankRemote proxy = (BankRemote) ctx.lookup(objectName);
			
			
			proxy.addAccount(new Account());
			proxy.addAccount(new Account());
			proxy.addAccount(new Account());
			
			Account acc = proxy.getAccount(1L);
			System.out.println(acc.getBalance());
			proxy.credit(1L, 4000);
			proxy.withdraw(1L, 1500);
			proxy.transfer(1L, 2L, 1000);
			List<Account> accts = proxy.listAccounts();
			for(Account a: accts) {
				System.out.println(a.getCode() + " : " + a.getBalance());
			}
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
