import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

interface Input_Menu{
	int Input=1, Search=2, Delete=3, Exit=4;
}

interface Input_Select{
	int Normal=1, Univ=2, Company=3;
}

class PhoneInfo
{
	String name;
	String phoneNumber;
	//String birthday;
	
	public PhoneInfo(String name, String phoneNumber)
	{
		this.name=name;
		this.phoneNumber = phoneNumber;
		//this.birthday = birthday;
	}
	
	public void ShowPhoneInfo(){
		System.out.println("Name : "+name);
		System.out.println("Phone : "+phoneNumber);
	}
	
	public int hashCode()
	{
		return name.hashCode();
	}
	
	public boolean equals(Object obj)
	{
		PhoneInfo pInfo = (PhoneInfo)obj;
		if(name.compareTo(pInfo.name)==0)
			return true;
		else
			return false;
	}
}

class PhoneUnivInfo extends PhoneInfo
{
	String major;
	int year;
	
	public PhoneUnivInfo(String name, String phoneNumber, String major, int year)
	{
		super(name, phoneNumber);
		this.major=major;
		this.year=year;
	}
	
	public void ShowPhoneInfo()
	{
		super.ShowPhoneInfo();
		System.out.println("Major : "+major);
		System.out.println("Year : "+year);
	}
	
}

class PhoneCompanyInfo extends PhoneInfo
{
	String company;
	
	public PhoneCompanyInfo(String name, String phoneNumber, String company)
	{
		super(name, phoneNumber);
		this.company=company;
	}

	public void ShowPhoneInfo()
	{
		super.ShowPhoneInfo();
		System.out.println("Compamy : "+company);
	}
	
}
class Manager
{
	/*final int MAX_CNT=100;
	PhoneInfo[] InfoStorage = new PhoneInfo[MAX_CNT];
	int CurCnt=0;*/
	
	HashSet<PhoneInfo> InfoStorage = new HashSet<PhoneInfo>();
		
	static Manager instance = null;
	
	public static Manager createManagerInst() // Manager instance have to be only one constructed.
	{
		if(instance==null)
		{
			instance=new Manager();
		}
		return instance;
	}
	
	public PhoneInfo readBasicInfo()
	{
		System.out.print("�̸� : ");
		String _name = Menu.keyboard.nextLine();
	
		System.out.print("��ȭ��ȣ : ");
		String _phone = Menu.keyboard.nextLine();
		
		return new PhoneInfo(_name, _phone);
	}
	
	public PhoneInfo readUnivInfo()
	{
		System.out.print("�̸� : ");
		String _name = Menu.keyboard.nextLine();
	
		System.out.print("��ȭ��ȣ : ");
		String _phone = Menu.keyboard.nextLine();
		
		System.out.print("���� : ");
		String _major = Menu.keyboard.nextLine();
		
		System.out.print("�г� : ");
		int _year = Menu.keyboard.nextInt();
		Menu.keyboard.nextLine();
		return new PhoneUnivInfo(_name, _phone, _major, _year);
	}
	
	public PhoneInfo readCompanyInfo()
	{
		System.out.print("�̸� : ");
		String _name = Menu.keyboard.nextLine();
	
		System.out.print("��ȭ��ȣ : ");
		String _phone = Menu.keyboard.nextLine();
		
		System.out.print("ȸ�� : ");
		String _company = Menu.keyboard.nextLine();
		
		return new PhoneCompanyInfo(_name, _phone, _company);
	}
	
	public void Data_Insert() throws ChoiceException
	{
		try{
			System.out.println("������ �Է��� �����մϴ�..");
		
			System.out.println("1.�Ϲ�, 2.����, 3.ȸ��");
			System.out.print("����>> ");
			int choice=Menu.keyboard.nextInt();
			Menu.keyboard.nextLine();
			PhoneInfo info = null;
		
			if(choice<Input_Select.Normal || choice>Input_Select.Company)
			{
				throw new ChoiceException(choice);
			}
			switch(choice)
			{
				case Input_Select.Normal:
					info=readBasicInfo();
					break;
				case Input_Select.Univ:
					info=readUnivInfo();
					break;
				case Input_Select.Company:
					info=readCompanyInfo();
					break;
			}

			//InfoStorage[CurCnt++]=info;
			
			boolean addInfo = InfoStorage.add(info);
			if(addInfo==true)
				System.out.println("������ �Է��� �Ϸ�Ǿ����ϴ�.\n");
			else
				System.out.println("�̹� ����� �������Դϴ�.\n");
			
		}catch(ChoiceException e)
		{
			e.ChoiceExcptShow();
			System.out.println("�޴� ������ ó������ �ٽ� �����մϴ�.\n");
		}
		
	}

	
	
	public void Data_Search()
	{
		System.out.println("������ �˻��� �����մϴ�..");
		System.out.print("�̸�  : ");
		String _name = Menu.keyboard.nextLine();
		
		PhoneInfo info = search(_name);
		
		if(info==null)
		{
			System.out.println("�ش� �����Ͱ� �����ϴ�.\n");
		}
		else
		{
			info.ShowPhoneInfo();
			System.out.println("������ �˻��� �Ϸ�Ǿ����ϴ�.\n");
		}
		/*int sIdx = search(_name);
		
		
		if(sIdx == -1)
		{
			System.out.println("�ش� �����Ͱ� �����ϴ�.\n");
		}
		else
		{
			InfoStorage[sIdx].ShowPhoneInfo();
			System.out.println("������ �˻��� �Ϸ�Ǿ����ϴ�.\n");
		}*/
		
	}
	
	public void Data_Delete()
	{
		System.out.println("������ ������ �����մϴ�..");
		System.out.print("�̸� : ");
		String _name = Menu.keyboard.nextLine();
		
        Iterator<PhoneInfo> itr = InfoStorage.iterator();
		
		while(itr.hasNext())
		{
			PhoneInfo curInfo = itr.next();
			if(_name.compareTo(curInfo.name)==0)
			{
				itr.remove();
				System.out.println("������ ������ �Ϸ�Ǿ����ϴ�.\n");
				return;
			}
		}
		
		System.out.println("�ش� �����Ͱ� �����ϴ�.\n");
		
		/*PhoneInfo Info = search(_name);
    	int dIdx = search(_name);
		if(dIdx==-1)
		{
			System.out.println("�ش� �����Ͱ� �����ϴ�.\n");
		}
		else
		{
			InfoStorage[dIdx]=InfoStorage[dIdx+1];
			CurCnt--;
			
			System.out.println("������ ������ �Ϸ�Ǿ����ϴ�.\n");
		}*/
		
		
		
	}
	
	private PhoneInfo search(String name)
	{
		/*for(int idx=0; idx<CurCnt; idx++)
		{
			//PhoneInfo curInfo = InfoStorage[idx];
			if((InfoStorage[idx].name).compareTo(name)==0)
				return idx;
		}
		*/
		Iterator<PhoneInfo> itr = InfoStorage.iterator();
		while(itr.hasNext())
		{
			PhoneInfo curInfo = itr.next();
			if(name.compareTo(curInfo.name)==0)
				return curInfo;
		}
		return null;
	}
}

class ChoiceException extends Exception
{
	int inputchoice;
	
	public ChoiceException(int choice)
	{
		super("�߸��� ������ �̷�������ϴ�.");
		inputchoice = choice;
	}
	public void ChoiceExcptShow()
	{
		System.out.println(inputchoice+"�� �ش��ϴ� ������ �������� �ʽ��ϴ�.");
	}
}
class Menu{
	
	public static Scanner keyboard = new Scanner(System.in);
	
	public static void ShowMenu()
	{
		System.out.println("�����ϼ���..");
		System.out.println("1.������ �Է�");
		System.out.println("2.������ �˻�");
		System.out.println("3.������ ����");
		System.out.println("4.���α׷� ����");
		System.out.print("���� : ");
	}
}

/*class MenuInputException extends Exception
{
	int menuchoice;
	
	public MenuInputException(int choice)
	{
		super("�߸��� ������ �̷�������ϴ�.");
		menuchoice=choice;
	}
	
	public void MenuExceptShow()
	{
		System.out.println(menuchoice+"�� �ش��ϴ� ������ �������� �ʽ��ϴ�.");
	}
}*/

public class PhoneBook {
	
	public static void main(String[] args) throws ChoiceException
	{
		boolean on_off = true;
		int choice=0;	    
		Manager manager = Manager.createManagerInst();
				
		while(on_off)
		{
			try
			{
				Menu.ShowMenu();
		     	choice = Menu.keyboard.nextInt();
		    	Menu.keyboard.nextLine();
		    	
		    	if(choice<Input_Menu.Input || choice>Input_Menu.Exit)
		    	{
		    		throw new ChoiceException(choice);
		    	}
		    	
		    	switch(choice){
		    		case Input_Menu.Input: 
		    			manager.Data_Insert();
		    			break;
		    		case Input_Menu.Search:
		    			manager.Data_Search();
		    			break;
		    		case Input_Menu.Delete:
		    			manager.Data_Delete();
		    			break;
		    		case Input_Menu.Exit:
		    			System.out.println("���α׷��� ����Ǿ����ϴ�.");
		    			System.out.println("");
		    			on_off=false;
		    			break;
		    		default:
		    			System.out.println("�ٽ� �Է����ּ���! (1~4 �߿� ����)");
		    			System.out.println("");
		    			break;
		    	}
					
			}catch(ChoiceException e)
			{
				e.ChoiceExcptShow();
				System.out.println("�޴� ������ ó������ �ٽ� �����մϴ�.\n");
			}
			
		}
		
		
	}

}