package com.ben.aoc.maths;

import java.math.BigInteger;

/**
 * A function f(x) = ax + b mod m
 * @author ben
 *
 */
public class LinearCongruenceFunction {
	
	private BigInteger a;
	private BigInteger b;
	private BigInteger m;
	
	public LinearCongruenceFunction(BigInteger a, BigInteger b, BigInteger m) {
		this.a = a;
		this.b = b;
		this.m = m;
	}
	
	public BigInteger solveX(BigInteger x) {
		BigInteger result = x;
		result = result.multiply(a);
		result = result.add(b);
		result = result.mod(m);
		
		return result;
	}
	
	/**
	 * Finds y s.t f(y) = x
	 * 
	 * @param x
	 * @return
	 */
	public BigInteger solveInverse(BigInteger x) {
		BigInteger result = x.subtract(b);
		result = result.multiply(a.modInverse(m));
		
		return result.mod(m);
	}
	
	/**
	 * composes this (g) with another (f)
	 * returning g(f(x))
	 * 
	 * @param lcf
	 * @return a single h(x) = g(f(x))
	 * @throws IllegalArgumentException if the modulus of the two functions aren't equal
	 */
	public LinearCongruenceFunction compose(LinearCongruenceFunction lcf) throws IllegalArgumentException{
		if(!m.equals(lcf.getm())){
			throw new IllegalArgumentException("modulus is not equal");
		}
		
		BigInteger a1 = lcf.geta().multiply(a);
		a1 = a1.mod(m);
		BigInteger b1 = lcf.getb().multiply(a);
		b1 = b1.add(b);
		b1 = b1.mod(m);
		return new LinearCongruenceFunction(a1, b1, m);
	}
	
	public BigInteger geta() {
		return a;
	}
	
	public BigInteger getb() {
		return b;
	}
	
	public BigInteger getm() {
		return m;
	}
	
	public LinearCongruenceFunction selfCompose(BigInteger composeCount) {
		BigInteger a1 = a.modPow(composeCount, m);
		BigInteger oneMinusA = BigInteger.ONE;
		oneMinusA = oneMinusA.subtract(a);//.mod(m);
		BigInteger b1 = BigInteger.ONE;
		b1 = b1.subtract(a1);
		b1 = b1.multiply(b);
		b1 = b1.multiply(oneMinusA.modInverse(m));
		b1 = b1.mod(m);
		
		return new LinearCongruenceFunction(a1, b1, m);
	}

}
