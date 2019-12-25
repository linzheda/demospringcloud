package com.linzd.pc.util;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class ExcelImporter {
	

	/**
	 * 
	 * @param <T>
	 *            模型T
	 * @param in
	 *            Excel的输入流
	 * @param sheetName
	 *            Excel文件名
	 * @param entityClass
	 *            实体对象
	 * @param fieldMap
	 *            Excel中的中文猎头和类的英文属性的对应关系MAP
	 * @param uniqueFields
	 *            指定业务主键组合(既复合主键) ， 这些列的组合不能重复
	 * @return
	 * @throws ExcelException
	 */
	public static <T> List<T> excelToList(InputStream in, String sheetName, Class<T> entityClass,
			LinkedHashMap<String, String> fieldMap) throws ExcelException {
		// 定义要返回的list
		List<T> resultList = new ArrayList<T>();
		try {
			// 根据Excel数据源来创建WorkBook
			Workbook wb = Workbook.getWorkbook(in);
			// 获取工作表
			Sheet sheet = wb.getSheet(sheetName);
			// 获取工作表的有效行数 默认为0
			int realRows = 0;
			for (int i = 0; i < sheet.getRows(); i++) {
				int nullCols = 0;
				for (int j = 0; j < sheet.getColumns(); j++) {
					// 获取单元格内容
					Cell currentCell = sheet.getCell(j, i);
					if (currentCell == null || "".equals(currentCell.getContents().toString())) {
						nullCols++;
					}
				}
				if (nullCols == sheet.getColumns()) {
					break;
				} else {
					realRows++;
				}
			}
			if (realRows <= 1) {
				// 如果Excel中没有数据则提示报错
				throw new ExcelException("Excel中没有数据");
			}
			Cell[] firstRow = sheet.getRow(0);
			String[] excelFieldNames = new String[firstRow.length];
			// 获取Excel中的列名
			for (int i = 0; i < firstRow.length; i++) {
				excelFieldNames[i] = firstRow[i].getContents().toString().trim();
			}
			// 判断需要的字段是否否已经在Excel中存在了
			boolean isExist = true;
			// 将字段名数组转变成list集合
			List<String> excelFieldList = Arrays.asList(excelFieldNames);
			for (String cnName : fieldMap.keySet()) {
				if (!excelFieldList.contains(cnName)) {
					isExist = false;
					break;
				}
			}
			// 如果有列名不匹配 报错
			if (!isExist) {
				throw new ExcelException("Excel中缺少必要的字段，或字段名称有误！");
			}
			// 将列名和列号放入map中，这样方便我们通过列名拿到列号
			LinkedHashMap<String, Integer> colMap = new LinkedHashMap<String, Integer>();
			for (int i = 0; i < excelFieldNames.length; i++) {
				colMap.put(excelFieldNames[i], firstRow[i].getColumn());
			}
			// 将sheet转换为list
			for (int i = 1; i < realRows; i++) {
				// 新建要转换的对象
				T entity = entityClass.newInstance();
				// 给对象中的字段赋值
				for (Entry<String, String> entry : fieldMap.entrySet()) {
					// 获取中文字段名
					String cnNormalName = entry.getKey();
					// 获取英文字段名
					String enNormalName = entry.getValue();
					// 根据中文字段名获取列
					int col = colMap.get(cnNormalName);
					// 获取当前单元格的内容 去空格
					String content = sheet.getCell(col, i).getContents().toString().trim();
					// 给对象赋值
					setFieldValueByName(enNormalName, content, entity);
				}
				resultList.add(entity);
			}
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExcelException("导入Excel失败");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExcelException("导入Excel失败");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExcelException("导入Excel失败");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExcelException("导入Excel失败");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExcelException("导入Excel失败");
		}
		return resultList;
	}

	/**
	 * @MethodName : setFieldValueByName
	 * @Description : 根据字段名给对象的字段赋值
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @param o
	 *            对象
	 */
	private static void setFieldValueByName(String fieldName, Object fieldValue, Object o) throws Exception {

		Field field = getFieldByName(fieldName, o.getClass());
		if (field != null) {
			field.setAccessible(true);
			// 获取字段类型
			Class<?> fieldType = field.getType();

			// 根据字段类型给字段赋值
			if (String.class == fieldType) {
				field.set(o, String.valueOf(fieldValue));
			} else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
				field.set(o, Integer.parseInt(fieldValue.toString()));
			} else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
				field.set(o, Long.valueOf(fieldValue.toString()));
			} else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
				field.set(o, Float.valueOf(fieldValue.toString()));
			} else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
				field.set(o, Short.valueOf(fieldValue.toString()));
			} else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
				field.set(o, Double.valueOf(fieldValue.toString()));
			} else if (Character.TYPE == fieldType) {
				if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
					field.set(o, Character.valueOf(fieldValue.toString().charAt(0)));
				}
			} else if (Date.class == fieldType) {
				field.set(o, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue.toString()));
			} else {
				field.set(o, fieldValue);
			}
		} else {
			throw new ExcelException(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
		}
	}

	/**
	 * @MethodName : getFieldByName
	 * @Description : 根据字段名获取字段
	 * @param fieldName
	 *            字段名
	 * @param clazz
	 *            包含该字段的类
	 * @return 字段
	 */
	private static Field getFieldByName(String fieldName, Class<?> clazz) {
		// 拿到本类的所有字段
		Field[] selfFields = clazz.getDeclaredFields();

		// 如果本类中存在该字段，则返回
		for (Field field : selfFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		// 否则，查看父类中是否存在此字段，如果有则返回
		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null && superClazz != Object.class) {
			return getFieldByName(fieldName, superClazz);
		}

		// 如果本类和父类都没有，则返回空
		return null;
	}
}
