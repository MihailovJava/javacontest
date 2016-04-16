package com.atc.javacontest;

public class CorrelationResultIndex {
	/*
	 * коэффициент корреляции между рядами от -1 до 1
	 */
	public double correlation = 0.0;

	/*
	 * линейный множитель разницы значений между рядами
	 */
	public double correlationMultipleIndex = 0.0;

	/*
	 * линейная прибавка разницы значений между рядами
	 */
	public double correlationLagIndex = 0.0;
	
	/*
	 * другой индекс разницы значений между рядами (на усмотрение разработчика)
	 */
	public String anotherIndex = "";
	/*
	 * описание сути другого индекса разницы значений между рядами (на усмотрение разработчика)
	 */
	public String anotherIndexDesc = "";
	
	/*
	 * начало действия корреляции между рядами. Под наличием корреляции (correlation) понимается значение по модулю более 0.3
	 */
	public String startIndex = null;
	
	/*
	 * окончание действия корреляции между рядами.
	 */
	public String endIndex = null;


	@Override
	public String toString() {
		return "correlation " + correlation + "\n"
				+ "correlationMultipleIndex " + correlationMultipleIndex + "\n"
				+ "correlationLagIndex " + correlationLagIndex + "\n"
				+ (startIndex == null ? "" : ("startIndex " + startIndex) + "\n")
				+ (endIndex == null ? "" : ("endIndex " + endIndex + "\n"))
				+ (anotherIndex.isEmpty() ? "" : "anotherIndex " + anotherIndex + "\n");

	}


}
