import java.util.*;

class hospital
{
	List<doctor>doctr= new ArrayList<doctor>();
	List<patient>patie= new ArrayList<patient>();
	Map<String,String> mp= new HashMap<String,String>();
	
	public void mapping()
	{
		mp.put("fever", "alopathic");
		mp.put("cough", "alopathic");
		mp.put("heart", "cardiologist");
		mp.put("dental", "dentist");
		mp.put("child", "child_specialist");
	}
	public void addDoctor(doctor d)
	{
		doctr.add(d);
	}
	
	public void addPatient(patient p)
	{
		patie.add(p);
	}
	
	public void displayDoctor()
	{
		System.out.println("List of doctors added is-: \n");
		int n=doctr.size();
		for(int i=0;i<n;i++)
		{
			System.out.println("Name-: " +doctr.get(i).name);
			System.out.println("Speciality-: " +doctr.get(i).speciality +"\n\n");
		}
	}
	
	public void displayPatient()
	{
		System.out.println("List of patients added is-: \n");
		Iterator<patient> itr = patie.iterator();
	      
	      while(itr.hasNext()) 
	      {
	         patient element = (patient)itr.next();
	         System.out.println("\nName-: " +element.name +"\nDisease-: " +element.disease +"\nAge" +element.age +"\nGender" +element.gender);
	      }
	}
	
	public void pat_doc(String dis)
	{
		String special= mp.get(dis);
		System.out.println("Speciality-: " +special);
		int n= doctr.size();
		int flag=0;
		for(int i=0;i<n;i++)
		{
			if(doctr.get(i).speciality.equals(special))
			{
				flag=1;
				System.out.println("Your Doctor's name is-: " +doctr.get(i).name);
				break;
			}
		}
		if(flag==0)
		{
			System.out.println("Sorry we don't have doctor to cure your disease!!");
		}
	}
}
class doctor
{
	String name= new String();
	String speciality= new String();
	
	doctor(String nm, String special)
	{
		name=nm;
		speciality=special;
	}
	
}
 
class patient 
{
	String name= new String();
	String disease= new String();
	String gender= new String();
	float age;
	
	patient(String nm, String dis, String gend, float ag)
	{
		name=nm;
		disease=dis;
		gender=gend;
		age=ag;
	}
}
 
public class examplegenerics 
{
	public static void main(String args[])
	{
		int ch,flag=1;
		hospital h= new hospital();
		h.mapping();
		String name,speciality, disease, gender;
		float age;
		Scanner obj= new Scanner(System.in);
		while(flag==1)
		{
			System.out.println("\n1.Add Doctor \n2.Add Patient \n3.Display List of Doctors \n4.Display List of Patients \n5.Exit \nEnter your choice-: ");
			ch=obj.nextInt();
			switch(ch)
			{
			case 1:
				System.out.println("Enter name of Doctor-: ");
				name=obj.next();
				System.out.println("Enter speciality of Doctor-: ");
				speciality=obj.next();
				
				doctor d =new doctor(name,speciality);
				h.addDoctor(d);
				break;
				
			case 2:
				System.out.println("Enter name of Patient-: ");
				name=obj.next();
				System.out.println("Enter disease of Patient-: ");
				disease=obj.next();
				System.out.println("Enter gender of Patient-: ");
				gender=obj.next();
				System.out.println("Enter age of Patient-: ");
				age=obj.nextFloat();
				
				
				patient p= new patient(name,disease,gender,age);
				h.addPatient(p);
				h.pat_doc(disease);
				break;
				
			case 3:
				h.displayDoctor();
				break;
				
			case 4:
				h.displayPatient();
				break;
				
			case 5:
				flag=0;
				break;
				
				default:
					System.out.println("Enter valid choice!!");
					break;
			}
		}
	}
}
