package com.briup.ch13; 
 
 
 import java.util.Scanner; 
 /** 
   学生信息管理系统 
   每个学生信息保存到对象中 
   学生对象保存到数组中 
 */ 
 public class Tms { 
 	private Teacher[] tchs;//用于存储学生的信息 
 	private int index;	//用于记录数组中总共有几个学生 
 
 
 	//构造函数用于初始化对象中非静态属性 
 	public Tms(){ 
 		tchs = new Teacher[3]; 
 		index = 0; 
 	} 
 
 
 	/** 
 	  保存 
 	  @param  学生对象 
 	*/ 
 	public void save(Teacher tch){ 
 		//数组的长度不足以保存学生了，数组的扩展 
 		if(index >= tchs.length){ 
 			Teacher[] demo = new Teacher[tchs.length + 3]; 
 			//数组拷贝，stus -> demo 
 			System.arraycopy(tchs,0,demo,0,index); 
 			tchs = demo; 
 		} 
 		tchs[index++] = tch; 
 	} 
 
 
 	/** 
 	  查询所有的学生 
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
 		通过学生的id查找学生的信息 
 		 {{1001,terry,12},{1002,terry,12},null} 
 		 1002 
 	*/ 
 	public Teacher queryById(long id){ 
 		//获取该id所在数组中的索引 
 		int num = getIndexById(id); 
 		return num == -1?null:tchs[num]; 
 	} 
 
 
 	/** 
 		通过id获取学号为该id的学生在数组中的位置 
 		 {{1001,terry,12},{1002,terry,12},null} 
 		id = 1002 
 		 1 
 	*/ 
 	private int getIndexById(long id){ 
 		int num = -1;//该学生找不到 
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
  
 		修改学生信息 
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
 		删除学生信息 
 	    id  name   age 
 	stus = { 
 		{101 terry   12}, 
 		{103 tom   12}, 
 		{104 tom   12}, 
 		null, 
 	} 
 		102 
  
 		修改学生信息 
 	*/ 
 	public void deleteById(long id){ 
 		int num = getIndexById(id); 
 		for(int i=num;i<index-1;i++){ 
 			tchs[i] = tchs[i+1]; 
 		} 
 		tchs[--index] = null; 
 	} 
 	 
 	public void menu(){ 
 		System.out.println("********老师管理系统********"); 
 		System.out.println("*1. 查询所有老师信息"); 
 		System.out.println("*2. 录入老师信息"); 
 		System.out.println("*3. 删除老师信息"); 
 		System.out.println("*4. 查询单个老师信息"); 
 		System.out.println("*5. 修改老师信息"); 
 		System.out.println("*exit. 退出"); 
 		System.out.println("*help. 帮助"); 
 		System.out.println("****************************"); 
 	} 
 	/** 
 		主方法 
 	*/ 
 	public static void main(String[] args){ 
 		Tms tms = new Tms(); 
 		tms.menu(); 
 		Scanner sc = new Scanner(System.in); 
 		while(true){ 
 			System.out.print("请输入功能编号："); 
 			String option = sc.nextLine(); 
 			//System.out.println("接收了："+option); 
 			switch(option){ 
 				case "1": 
 					System.out.println("以下是老师的信息："); 
 					Teacher[] arr = tms.queryAll(); 
 					for(int i=0;i<arr.length;i++){ 
 						System.out.println(arr[i]); 					} 
 				        System.out.println("总计 "+tms.index+" 个"); 
 					       break; 
 				case "2": 
 					while(true){ 
 						System.out.println("请输入老师信息【id#name#age】或者输入【break】返回上一级目录"); 
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
 						System.out.println("保存成功！"); 
 					} 
 					 
 					break; 
 				case "3": 
 					while(true){ 
 						System.out.println("请输入要删除老师的工号或者输入【break】返回上一级目录"); 
 						String idStr = sc.nextLine(); 
 						if(idStr.equals("break")){ 
 							break;//跳出当前循环，返回主菜单 
 						} 
 						//1001#terry#12 
 						long id = Long.parseLong(idStr); 
 						Teacher oldTch = tms.queryById(id); 
 						if(oldTch == null){ 
							System.out.println("您要删除的老师不存在！"); 
 							continue; 
 						} 
 						tms.deleteById(id); 
 						System.out.println("删除成功！"); 
 					} 
 					break; 
 				case "4": 
 					while(true){ 
 						System.out.println("请输入工号或者输入【break】返回上一级目录"); 
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
 				case "5"://修改 
 					while(true){ 
 						System.out.println("请输入要修改老师的工号或者输入【break】返回上一级目录"); 
 						String idTch = sc.nextLine(); 
 						if(idTch.equals("break")){ 
 							break;//跳出当前循环，返回主菜单 
 						} 
 						//1001#terry#12 
 						long id = Long.parseLong(idTch); 
 						Teacher oldTch = tms.queryById(id); 
 						if(oldTch == null){ 
 							System.out.println("您要修改的老师不存在！"); 
 							continue; 
 						} 
						System.out.println("原有信息为："+oldTch); 
 						System.out.println("请输入信息【name#age】"); 
 						//获取用户的新信息，并且将其封装为对象 
 						String newTch = sc.nextLine(); 
 						String[] newArr = newTch.split("#"); 
 						String name = newArr[0]; 
 						int age = Integer.parseInt(newArr[1]); 
 
 
 						Teacher nTch = new Teacher(id,name,age); 
 						//调用修改模块的方法，完成修改功能 
 						tms.update(nTch); 
 						System.out.println("修改成功"); 
 					} 
 					break; 
 				case "exit": 
 					System.out.println("bye bye,欢迎再次使用！"); 
 					System.exit(0); 
 				case "help": 
 					tms.menu(); 
 					break; 
 				default: 
 					System.out.println("不合法输入！"); 
 
 
 			} 
 		} 
 	} 
 } 
