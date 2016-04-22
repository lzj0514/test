package com.briup.ch13; 
 
 
 import java.util.Scanner; 
 /** 
   ѧ����Ϣ����ϵͳ 
   ÿ��ѧ����Ϣ���浽������ 
   ѧ�����󱣴浽������ 
 */ 
 public class Tms { 
 	private Teacher[] tchs;//���ڴ洢ѧ������Ϣ 
 	private int index;	//���ڼ�¼�������ܹ��м���ѧ�� 
 
 
 	//���캯�����ڳ�ʼ�������зǾ�̬���� 
 	public Tms(){ 
 		tchs = new Teacher[3]; 
 		index = 0; 
 	} 
 
 
 	/** 
 	  ���� 
 	  @param  ѧ������ 
 	*/ 
 	public void save(Teacher tch){ 
 		//����ĳ��Ȳ����Ա���ѧ���ˣ��������չ 
 		if(index >= tchs.length){ 
 			Teacher[] demo = new Teacher[tchs.length + 3]; 
 			//���鿽����stus -> demo 
 			System.arraycopy(tchs,0,demo,0,index); 
 			tchs = demo; 
 		} 
 		tchs[index++] = tch; 
 	} 
 
 
 	/** 
 	  ��ѯ���е�ѧ�� 
 	  stus= new Student[3]; 
 	  {{1001,terry,12},{1002,terry,12},null} 
 	  {{1001,terry,12},{1002,terry,12}} 
 	  index = 1 
 	*/ 
 	public Teacher[] queryAll(){ 
 		Teacher[] demo = new Teacher[index]; 
 		System.arraycopy(tchs,0,demo,0,index); 
 		return demo; 
 	} 
 
 
 	/** 
 		ͨ��ѧ����id����ѧ������Ϣ 
 		 {{1001,terry,12},{1002,terry,12},null} 
 		 1002 
 	*/ 
 	public Teacher queryById(long id){ 
 		//��ȡ��id���������е����� 
 		int num = getIndexById(id); 
 		return num == -1?null:tchs[num]; 
 	} 
 
 
 	/** 
 		ͨ��id��ȡѧ��Ϊ��id��ѧ���������е�λ�� 
 		 {{1001,terry,12},{1002,terry,12},null} 
 		id = 1002 
 		 1 
 	*/ 
 	private int getIndexById(long id){ 
 		int num = -1;//��ѧ���Ҳ��� 
 		for(int i=0;i<index;i++){ 
 			if(tchs[i].getId() == id){ 
 				num = i; 
 				break; 
 			} 
 		} 
 		return num; 
 	} 
 	/** 
 	    id  name   age 
 		101 terry   12 
 		102 jacky   12 
  
 		102 jacky  12 
  
 		�޸�ѧ����Ϣ 
 	*/ 
 	public void update(Teacher newTch){ 
 		for(int i=0;i<index;i++){ 
 			if(newTch.getId() == tchs[i].getId()){ 
 				tchs[i].setName(newTch.getName()); 
 				tchs[i].setAge(newTch.getAge()); 
 			} 
 		} 
 	} 
    /** 
 		ɾ��ѧ����Ϣ 
 	    id  name   age 
 	stus = { 
 		{101 terry   12}, 
 		{103 tom   12}, 
 		{104 tom   12}, 
 		null, 
 	} 
 		102 
  
 		�޸�ѧ����Ϣ 
 	*/ 
 	public void deleteById(long id){ 
 		int num = getIndexById(id); 
 		for(int i=num;i<index-1;i++){ 
 			tchs[i] = tchs[i+1]; 
 		} 
 		tchs[--index] = null; 
 	} 
 	 
 	public void menu(){ 
 		System.out.println("********��ʦ����ϵͳ********"); 
 		System.out.println("*1. ��ѯ������ʦ��Ϣ"); 
 		System.out.println("*2. ¼����ʦ��Ϣ"); 
 		System.out.println("*3. ɾ����ʦ��Ϣ"); 
 		System.out.println("*4. ��ѯ������ʦ��Ϣ"); 
 		System.out.println("*5. �޸���ʦ��Ϣ"); 
 		System.out.println("*exit. �˳�"); 
 		System.out.println("*help. ����"); 
 		System.out.println("****************************"); 
 	} 
 	/** 
 		������ 
 	*/ 
 	public static void main(String[] args){ 
 		Tms tms = new Tms(); 
 		tms.menu(); 
 		Scanner sc = new Scanner(System.in); 
 		while(true){ 
 			System.out.print("�����빦�ܱ�ţ�"); 
 			String option = sc.nextLine(); 
 			//System.out.println("�����ˣ�"+option); 
 			switch(option){ 
 				case "1": 
 					System.out.println("��������ʦ����Ϣ��"); 
 					Teacher[] arr = tms.queryAll(); 
 					for(int i=0;i<arr.length;i++){ 
 						System.out.println(arr[i]); 					} 
 				        System.out.println("�ܼ� "+tms.index+" ��"); 
 					       break; 
 				case "2": 
 					while(true){ 
 						System.out.println("��������ʦ��Ϣ��id#name#age���������롾break��������һ��Ŀ¼"); 
 						String tchStr = sc.nextLine(); 
 						if(tchStr.equals("break")){ 
 							break; 
 						} 
 						//1001#terry#12 
 						String[] tchArr = tchStr.split("#"); 
 						long id = Long.parseLong(tchArr[0]); 
 						String name = tchArr[1]; 
 						int age = Integer.parseInt(tchArr[2]); 
 						Teacher tch = new Teacher(id,name,age); 
 						tms.save(tch); 
 						System.out.println("����ɹ���"); 
 					} 
 					 
 					break; 
 				case "3": 
 					while(true){ 
 						System.out.println("������Ҫɾ����ʦ�Ĺ��Ż������롾break��������һ��Ŀ¼"); 
 						String idStr = sc.nextLine(); 
 						if(idStr.equals("break")){ 
 							break;//������ǰѭ�����������˵� 
 						} 
 						//1001#terry#12 
 						long id = Long.parseLong(idStr); 
 						Teacher oldTch = tms.queryById(id); 
 						if(oldTch == null){ 
							System.out.println("��Ҫɾ������ʦ�����ڣ�"); 
 							continue; 
 						} 
 						tms.deleteById(id); 
 						System.out.println("ɾ���ɹ���"); 
 					} 
 					break; 
 				case "4": 
 					while(true){ 
 						System.out.println("�����빤�Ż������롾break��������һ��Ŀ¼"); 
 						String idTch = sc.nextLine(); 
 						if(idTch.equals("break")){ 
 							break; 
 						} 
 						//1001#terry#12 
 						long id = Long.parseLong(idTch); 
 						    Teacher tch = tms.queryById(id); 
 					        System.out.println(tch==null?"sorry,not found!":tch); 
 					} 
 					break; 
 				case "5"://�޸� 
 					while(true){ 
 						System.out.println("������Ҫ�޸���ʦ�Ĺ��Ż������롾break��������һ��Ŀ¼"); 
 						String idTch = sc.nextLine(); 
 						if(idTch.equals("break")){ 
 							break;//������ǰѭ�����������˵� 
 						} 
 						//1001#terry#12 
 						long id = Long.parseLong(idTch); 
 						Teacher oldTch = tms.queryById(id); 
 						if(oldTch == null){ 
 							System.out.println("��Ҫ�޸ĵ���ʦ�����ڣ�"); 
 							continue; 
 						} 
						System.out.println("ԭ����ϢΪ��"+oldTch); 
 						System.out.println("��������Ϣ��name#age��"); 
 						//��ȡ�û�������Ϣ�����ҽ����װΪ���� 
 						String newTch = sc.nextLine(); 
 						String[] newArr = newTch.split("#"); 
 						String name = newArr[0]; 
 						int age = Integer.parseInt(newArr[1]); 
 
 
 						Teacher nTch = new Teacher(id,name,age); 
 						//�����޸�ģ��ķ���������޸Ĺ��� 
 						tms.update(nTch); 
 						System.out.println("�޸ĳɹ�"); 
 					} 
 					break; 
 				case "exit": 
 					System.out.println("bye bye,��ӭ�ٴ�ʹ�ã�"); 
 					System.exit(0); 
 				case "help": 
 					tms.menu(); 
 					break; 
 				default: 
 					System.out.println("���Ϸ����룡"); 
 
 
 			} 
 		} 
 	} 
 } 
