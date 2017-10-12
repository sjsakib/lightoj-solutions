/**
 * Author    : sjsakib.bd
 * Lang      : JAVA
 * Date      : 2015-10-02 10:58:54
 * Problem   : 1024 - Eid
 * CPU       : 0.736
 * Memory    : 28404
**/
import java.util.*;
import java.io.*;
import java.util.concurrent.*;
import java.io.IOException;
import java.io.InputStream;
public class Main {
    static PrimeList pl; 
    public static void main(String[] args) {
        FasterScanner in = new FasterScanner();
        pl = new PrimeList(100);
        int t = in.nextInt();
        BigInt res = new BigInt(1);
        int[][] list = new int[1000][15];

        int n;

        for(int i = 0;i<t;i++) {
            n = in.nextInt();
            res.assign(1);

            
            for(int k = 0;k<n;k++) {
                factorize(list[k],in.nextInt());
            }
            for(int k = 0;k<n;k++) {
                for(int x = 0;list[k][x]!=-1;x++) {
                    if(list[k][x] == 1) {
                        continue;
                    }
                    res.mul(list[k][x]);
                    for(int y = k+1;y<n;y++) {
                        for(int z = 0;z<list[k].length;z++) {
                            if(list[k][x] == list[y][z]) {
                                list[y][z] = 1;
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println("Case "+(i+1)+": "+res.toString());
        }
        
    }
    static void factorize(int[] array,int x) {
        int j = 0;
        int p = pl.next();
        while(p!=-1 && x!=1) {
            while(x%p == 0) {
                x = x/p;
                array[j++] = p;
            }
            p = pl.next();
            if(p == -1 && x!=1) {
                array[j++] = x;
            }
        }
        array[j++] = -1;
        pl.reset();
    }
}


class PrimeList {
    int[] list;
    private int  n;
    int current;

    PrimeList(int n_) {
        current = 0;
        n = 0;
        int[] s = new int[n_+1];
        for(int i = 2;i<Math.sqrt(n_);i++) {
            if(s[i]!=1) {
                for(int k = 2;i*k<=n_;k++) {
                    s[i*k] = 1;
                }
            }
        }
        list = new int[25];
        for(int i = 2;i<n_;i++) {
            if(s[i]!=1) {
                list[n++] = i;
            }
        }
    }
    int next() {
        if(current>=n) {
            return -1;
        } else {
            return list[current++];
        }
    }
    void reset() {
        current = 0;
    }
}

class BigInt extends Number implements Comparable<BigInt>
{
	private static final long mask = (1L<<32) - 1;


	private int sign;

	private int len;

	private int[] dig;

	public BigInt(final int sign, final int[] v, final int len)
	{
		assign(sign,v,len);
	}

	public BigInt(final int sign, final int val)
	{
		dig = new int[1];
		uassign(sign,val);
	}

	public BigInt(final int sign, final long val)
	{
		dig = new int[2];
		uassign(sign,val);
	}

	public BigInt(final int val)
	{
		dig = new int[1];
		assign(val);
	}

	public BigInt(final long val)
	{
		dig = new int[2];
		assign(val);
	}

	public BigInt(final String s)
	{
		assign(s);
	}

	public BigInt(final char[] s)
	{
		assign(s);
	}

	private int parse(final char[] s, int from, final int to)
	{
		int res = s[from]-'0';
		while(++from<to) res = res*10 + s[from]-'0';
		return res;
	}

	private void mulAdd(final int mul, final int add)
	{
		long carry = 0;
		for(int i = 0; i<len; i++)
		{
			carry = mul * (dig[i]&mask) + carry;
			dig[i] = (int)carry;
			carry >>>= 32;
		}
		if(carry!=0) dig[len++] = (int)carry;
		carry = (dig[0]&mask) + add;
		dig[0] = (int)carry;
		if((carry >>> 32)!=0)
		{
			int i = 1;
			for(; i<len && ++dig[i]==0; ++i);
			if(i==len) dig[len++] = 1; //Todo: realloc() for general case?
		}
	}

	private void realloc()
	{
		final int[] res = new int[dig.length*2];
		System.arraycopy(dig,0,res,0,len);
		dig = res;
	}

	private void realloc(final int newLen)
	{
		final int[] res = new int[newLen];
		System.arraycopy(dig,0,res,0,len);
		dig = res;
	}

	public BigInt copy()
	{
		return new BigInt(sign, Arrays.copyOf(dig,len), len);
	}

	private void assign(final int[] v, final int vlen) //Todo: Better and more consistent naming.
	{
		if(vlen>dig.length) dig = new int[vlen+2];
		System.arraycopy(v,0,dig,0,len=vlen);
	}

	public void assign(final int sign, final int[] v, final int len)
	{
		this.sign = sign; this.len = len;
		dig = v;
	}

	public void assign(final String s)
	{
		assign(s.toCharArray());
	}

	public void assign(final char[] s)
	{
		sign = s[0]=='-' ? -1 : 1;

		len = s.length + (sign-1>>1);
		final int alloc = len<10 ? 1 : (int)(len*3402L >>> 10) + 32 >>> 5; //3402 = bits per digit * 1024
		if(dig==null || alloc>dig.length) dig = new int[alloc];

		int j = len%9;
		if(j==0) j = 9;
		j -= (sign-1>>1);

		dig[0] = parse(s, 0-(sign-1>>1), j);
		for(len = 1; j<s.length;)
			mulAdd(1_000_000_000, parse(s,j,j+=9));
	}

	public void uassign(final int s, final int val)
	{
		sign = s;
		len = 1;
		dig[0] = val;
	}

	public void uassign(final int s, final long val)
	{
		sign = s;
		len = 2;
		if(dig.length<2) realloc(2);
		dig[0] = (int)(val&mask);
		dig[1] = (int)(val>>>32);
		if(dig[1]==0) --len;
	}

	public void uassign(final int val)
	{
		uassign(1, val);
	}

	public void uassign(final long val)
	{
		uassign(1, val);
	}

	public void assign(final int val)
	{
		uassign(val<0 ? -1 : 1, val<0 ? -val : val);
	}

	public void assign(final long val)
	{
		uassign(val<0 ? -1 : 1, val<0 ? -val : val);
	}

	public boolean isZero()
	{
		return len==1 && dig[0]==0;
	}

	private void setToZero()
	{
		dig[0] = 0;
		len = 1; sign = 1; //Remove?
	}

	public int compareAbsTo(final BigInt a)
	{
		if(len>a.len) return 1;
		if(len<a.len) return -1;
		for(int i = len-1; i>=0; i--)
			if(dig[i]!=a.dig[i])
				if((dig[i]&mask)>(a.dig[i]&mask)) return 1;
				else return -1;
		return 0;
	}

	public int compareTo(final BigInt a)
	{
		if(sign<0)
		{
			if(a.sign<0 || a.isZero()) return -compareAbsTo(a);
			return -1;
		}
		if(a.sign>0 || a.isZero()) return compareAbsTo(a);
		return 1;
	}

	public boolean equals(final BigInt a)
	{
		if(len!=a.len) return false;
		if(isZero() && a.isZero()) return true;
		if((sign^a.sign)<0) return false; //In case definition of sign would change...
		for(int i = 0; i<len; i++) if(dig[i]!=a.dig[i]) return false;
		return true;
	}

	@Override
	public boolean equals(final Object o) //Todo: Equality on other Number objects?
	{
		if(o instanceof BigInt) return equals((BigInt)o);
		return false;
	}

	@Override
	public int hashCode()
	{
		int hash = 0; //Todo: Opt and improve.
		for(int i = 0; i<len; i++) hash = (int)(31*hash + (dig[i]&mask));
		return sign*hash; //relies on 0 --> hash==0.
	}

	@Override
	public byte byteValue()
	{
		return (byte)(sign*(dig[0]&0x7F));
	}

	@Override
	public short shortValue()
	{
		return (short)(sign*(dig[0]&0x7FFF));
	}

	@Override
	public int intValue()
	{
		return sign*(dig[0]&0x7FFFFFFF); //relies on that sign always is either 1/-1.
	}

	@Override
	public long longValue()
	{
		return len==1 ? sign*(dig[0]&mask) : sign*((dig[1]&0x7FFFFFFFL)<<32|(dig[0]&mask));
	}

	@Override
	public float floatValue()
	{
		final int s = Integer.numberOfLeadingZeros(dig[len-1]);
		if(len==1 && s>=8) return sign*dig[0];

		int bits = dig[len-1]; //Mask out the 24 MSBits.
		if(s<=8) bits >>>= 8-s;
		else bits = bits<<s-8 | dig[len-2]>>>32-(s-8); //s-8==additional bits we need.
		bits ^= 1L<<23; //The leading bit is implicit, cancel it out.

		final int exp = (int)(((32-s + 32L*(len-1)) - 1 + 127)&0xFF);
		bits |= exp<<23; //Add exponent.
		bits |= sign&(1<<31); //Add sign-bit.

		return Float.intBitsToFloat(bits);
	}

	@Override
	public double doubleValue()
	{
		if(len==1) return sign*(dig[0]&mask);

		final int s = Integer.numberOfLeadingZeros(dig[len-1]);
		if(len==2 && 32-s+32<=53) return sign*((long)dig[1]<<32|(dig[0]&mask));

		long bits = (long)dig[len-1]<<32 | (dig[len-2]&mask); //Mask out the 53 MSBits.
		if(s<=11) bits >>>= 11-s;
		else bits = bits<<s-11 | dig[len-3]>>>32-(s-11); //s-11==additional bits we need.
		bits ^= 1L<<52; //The leading bit is implicit, cancel it out.

		final long exp = ((32-s + 32L*(len-1)) - 1 + 1023)&0x7FF;
		bits |= exp<<52; //Add exponent.
		bits |= (long)sign&(1L<<63); //Add sign-bit.

		return Double.longBitsToDouble(bits);
	}

	private void uaddMag(final int a)
	{
		long tmp = (dig[0]&mask) + (a&mask);
		dig[0] = (int)tmp;
		if((tmp>>>32)!=0)
		{
			int i = 1;
			for(; i<len && ++dig[i]==0; i++);
			if(i==len)
			{
				if(len==dig.length) realloc();
				dig[len++] = 1;
			}
		}
	}

	private void usubMag(final int s)
	{
		long dif = (dig[0]&mask) - (s&mask);
		dig[0] = (int)dif;
		if((dif>>32)!=0)
		{
			int i = 1;
			for(; dig[i]==0; i++) --dig[i];
			if(--dig[i]==0 && i+1==len) --len;
		}
	}

	public void uadd(final int a)
	{
		if(sign<0)
		{
			if(len>1 || (dig[0]&mask)>(a&mask)){ usubMag(a); return; }
			sign = 1;
			dig[0] = a-dig[0]; return;
		}

		uaddMag(a);
	}

	public void usub(final int s)
	{
		if(sign<0)
		{
			uaddMag(s);
			return;
		}
		if(len==1 && (dig[0]&mask)<(s&mask)){ sign = -1; dig[0] = s-dig[0]; return; }

		usubMag(s);
	}

	public void umul(final int mul) //mul is interpreted as unsigned
	{
		if(mul==0){ setToZero(); return; } //To be removed?

		long carry = 0; final long m = mul&mask;
		for(int i = 0; i<len; i++)
		{
			carry = (dig[i]&mask)*m + carry;
			dig[i] = (int)carry;
			carry >>>= 32;
		}
		if(carry!=0)
		{
			if(len==dig.length) realloc();
			dig[len++] = (int)carry;
		}
	}

	public int udiv(final int div) //returns the unsigned remainder!
	{
		final long d = div&mask;
		long rem = 0;
		for(int i = len-1; i>=0; i--)
		{
			rem <<= 32;
			rem = rem + (dig[i]&mask);
			dig[i] = (int)(rem/d); //Todo: opt?
			rem = rem%d;
		}
		if(dig[len-1]==0 && len>1) --len;
		//if(len==1 && dig[0]==0) sign = 1; toString() relies on no sign-swap for 0.
		return (int)rem;
	}

	public void urem(final int mod)
	{
		long rem = 0, d = mod&mask;
		for(int i = len-1; i>=0; i--)
		{
			rem <<= 32;
			rem = (rem + (dig[i]&mask))%d;
		}
		len = 1;
		dig[0] = (int)rem;
		//if(dig[0]==0) sign = 1;
	}

	private void uaddMag(final long a)
	{
		if(dig.length<=2){ realloc(3); len = 2; }

		final long ah = a>>>32, al = a&mask;
		long carry = (dig[0]&mask) + al;
		dig[0] = (int)carry;
		carry >>>= 32;
		carry = (dig[1]&mask) + ah + carry;
		dig[1] = (int)carry;
		if((carry>>32)!=0)
		{
			int i = 2;
			for(; i<len && ++dig[i]==0; i++);
			if(i==len)
			{
				if(len==dig.length) realloc();
				dig[len++] = 1;
			}
		}
	}

	private void usubMag(final long s)
	{
		final long sh = s>>>32, sl = s&mask;
		long dif = (dig[0]&mask) - sl;
		dig[0] = (int)dif;
		dif >>= 32;
		dif = (dig[1]&mask) - sh + dif;
		dig[1] = (int)dif;
		if((dif>>32)!=0)
		{
			int i = 2;
			for(; dig[i]==0; i++) --dig[i];
			if(--dig[i]==0 && i+1==len) --len;
		}
		if(len==2 && dig[1]==0) --len;
	}

	public void uadd(final long a) //Refactor? Similar to usub.
	{
		if(sign<0)
		{
			final long ah = a>>>32, al = a&mask;
			if(len>2 || (dig[1]&mask)>ah || (dig[1]&mask)==ah&&(dig[0]&mask)>=al)
			{
				usubMag(a); return;
			}
			if(dig.length==1) realloc(2);
			if(len==1) dig[len++] = 0;
			long dif = al - (dig[0]&mask);
			dig[0] = (int)dif;
			dif >>= 32;
			dif = ah - (dig[1]&mask) + dif;
			dig[1] = (int)dif;
			//dif>>32 != 0 should be impossible
			if(dif==0) --len;
			sign = 1;
		}
		else uaddMag(a);
	}

	public void usub(final long a) //Fix parameter name
	{
		if(sign>0)
		{
			final long ah = a>>>32, al = a&mask;
			if(len>2 || (dig[1]&mask)>ah || (dig[1]&mask)==ah&&(dig[0]&mask)>=al)
			{
				usubMag(a); return;
			}
			if(dig.length==1) realloc(2);
			if(len==1) dig[len++] = 0;
			long dif = al - (dig[0]&mask);
			dig[0] = (int)dif;
			dif >>= 32;
			dif = ah - (dig[1]&mask) + dif;
			dig[1] = (int)dif;
			//dif>>32 != 0 should be impossible
			if(dif==0) --len;
			sign = -1;
		}
		else uaddMag(a);
	}

	public void umul(final long mul)
	{
		if(mul==0){ setToZero(); return; }
		if(len+2>=dig.length) realloc(2*len+1);

		final long mh = mul>>>32, ml = mul&mask;
		long carry = 0, next = 0, tmp;
		for(int i = 0; i<len; i++)
		{
			carry = carry + next; //Could this overflow?
			tmp = (dig[i]&mask)*ml;
			next = (dig[i]&mask)*mh;
			dig[i] = (int)(tmp + carry);
			carry = (tmp>>>32)+(carry>>>32) + ((tmp&mask)+(carry&mask)>>>32);
		}
		carry = carry+next;
		dig[len++] = (int)carry;
		dig[len++] = (int)(carry>>>32);

		while(len>1 && dig[len-1]==0) --len;
	}

	public long udiv(final long div) //Adaption of general div to long.
	{
	   if(div==(div&mask)) return udiv((int)div)&mask;
	   if(len==1)
	   {
		   final long tmp = dig[0]&mask;
		   setToZero();
		   return tmp;
	   }

	   final int s = Integer.numberOfLeadingZeros((int)(div>>>32));
	   final long dh = div>>>32-s, dl = (div<<s)&mask, hbit = Long.MIN_VALUE;

	   long u2 = 0, u1 = dig[len-1]>>>32-s, u0 = (dig[len-1]<<s | dig[len-2]>>>32-s)&mask;
	   if(s==0){ u1 = 0; u0 = dig[len-1]&mask; }
	   for(int j = len-2; j>=0; j--)
	   {
	      u2 = u1; u1 = u0; u0 = s>0&&j>0 ? (dig[j]<<s | dig[j-1]>>>32-s)&mask : (dig[j]<<s)&mask;

	      long k = (u2<<32) + u1; //Unsigned division is a pain in the ass! ='(
	      long qhat = (k >>> 1)/dh << 1;
		  long t = k - qhat*dh;
		  if(t + hbit > dh + hbit) qhat++; // qhat = (u[j+n]*b + u[j+n-1])/v[n-1];
	      long rhat = k - qhat*dh;

	      while(qhat+hbit >= (1L<<32)+hbit || qhat*dl+hbit > (rhat<<32)+u0+hbit) //Unsigned comparison.
	      {
			  --qhat;
			  rhat = rhat + dh;
			  if(rhat+hbit >= (1L<<32)+hbit) break;
	      }

	      // Multiply and subtract. Unfolded loop.
	      long p = qhat*dl;
	      t = u0 - (p&mask);
	      u0 = t&mask;
	      k = (p>>>32) - (t>>32);
	      p = qhat*dh;
	      t = u1 - k - (p&mask);
	      u1 = t&mask;
	      k = (p>>>32) - (t>>32);
	      t = u2 - k;
	      u2 = t&mask;

	      dig[j] = (int)qhat; // Store quotient digit. If we subtracted too much, add back.
	      if(t<0)
	      {
	         --dig[j]; //Unfolded loop.
	         t = u0 + dl;
	         u0 = t&mask;
	       	 t >>>= 32;
	         t = u1 + dh + t;
	         u1 = t&mask;
	         t >>>= 32;
	         u2 += t&mask;
	      }
	   }

	   --len;
	   dig[len] = 0;
	   if(len>1 && dig[len-1]==0) --len;

	   final long tmp = u1<<32-s | u0>>>s;
	   return s==0 ? tmp : u2<<64-s | tmp;
	}

	public void urem(final long mod)
	{
		final long rem = udiv(mod); //todo: opt?
		len = 2;
		dig[0] = (int)rem;
		if(rem==(rem&mask)){ --len; return; } //if(dig[0]==0) sign = 1;
		dig[1] = (int)(rem>>>32);
	}

	public void add(final int add) //Has not amortized O(1) due to the risk of alternating +1 -1 on continuous sequence of 1-set bits.
	{
		if(add<0) usub(-add);
		else uadd(add);
	}

	public void sub(final int sub)
	{
		if(sub<0) uadd(-sub);
		else usub(sub);

	}
	public void mul(final int mul)
	{
		if(isZero()) return; //Remove?
		if(mul<0){ sign = -sign; umul(-mul); }
		else umul(mul);
	}

	public int div(final int div)
	{
		if(isZero()) return 0; //Remove?
		if(div<0){ sign = -sign; return -sign*udiv(-div); }
		return sign*udiv(div);
	}

	public void add(final long add)
	{
		if(add<0) usub(-add);
		else uadd(add);
	}
	/**
	* Subtracts a long from this number.
	*
	* @param sub	The amount to subtract.
	* @complexity	O(n)
	*/
	public void sub(final long sub)
	{
		if(sub<0) uadd(-sub);
		else usub(sub);
	}
	/**
	* Multiplies this number with a long.
	*
	* @param mul	The amount to multiply with.
	* @complexity	O(n)
	*/
	public void mul(final long mul)
	{
		if(isZero()) return; //remove?
		if(mul<0){ sign = -sign; umul(-mul); }
		else umul(mul);
	}
	/**
	* Divides this number with a {@code long}.
	*
	* @param div	The amount to divide with.
	* @complexity	O(n)
	* @return		the signed remainder.
	*/
	public long div(final long div)
	{
		if(isZero()) return 0; //Remove?
		if(div<0){ sign = -sign; return -sign*udiv(-div); }
		return sign*udiv(div);
	}
	private void addMag(int[] v, int vlen)
	{
		int ulen = len;
		int[] u = dig; //ulen <= vlen
		if(vlen<ulen){ u = v; v = dig; ulen = vlen; vlen = len; }
		if(vlen>dig.length) realloc(vlen+1);

		long carry = 0; int i = 0;
		for(; i<ulen; i++)
		{
			carry = (u[i]&mask) + (v[i]&mask) + carry;
			dig[i] = (int)carry;
			carry >>>= 32;
		}
		if(vlen>len){ System.arraycopy(v, len, dig, len, vlen-len); len = vlen; }
		if(carry!=0) //carry==1
		{
			for(; i<len && ++dig[i]==0; i++);
			if(i==len) //vlen==len
			{
				if(len==dig.length) realloc();
				dig[len++] = 1;
			}
		}
	}

	private void subMag(final int[] u, final int ulen)
	{
		final int vlen = len;
		final int[] v = dig; //ulen <= vlen

		//Assumes vlen=len and v=dig
		long dif = 0; int i = 0;
		for(; i<ulen; i++)
		{
			dif = (v[i]&mask) - (u[i]&mask) + dif;
			dig[i] = (int)dif;
			dif >>= 32;
		}
		if(dif!=0)
		{
			for(; dig[i]==0; i++) --dig[i];
			if(--dig[i]==0 && i+1==len) len = ulen;
		}
		while(len>1 && dig[len-1]==0) --len;
	}

	public void add(final BigInt a)
	{
		if(sign==a.sign){ addMag(a.dig,a.len); return; }
		if(compareAbsTo(a)>=0)
		{
			subMag(a.dig,a.len);
			//if(len==1 && dig[0]==0) sign = 1;
			return;
		}

		final int[] v = a.dig;
		final int vlen = a.len;
		if(dig.length<vlen) realloc(vlen+1);

		sign = -sign;
		long dif = 0; int i = 0;
		for(; i<len; i++)
		{
			dif = (v[i]&mask)-(dig[i]&mask) + dif;
			dig[i] = (int)dif;
			dif >>= 32;
		}
		if(vlen>len){ System.arraycopy(v, len, dig, len, vlen-len); len = vlen; }
		if(dif!=0)
		{
			for(; i<vlen && dig[i]==0; i++) --dig[i];
			if(--dig[i]==0 && i+1==len) --len;
		}
		//if(i==vlen) should be impossible
	}

	public void sub(final BigInt a) //Fix naming.
	{
		if(sign!=a.sign){ addMag(a.dig,a.len); return; }
		if(compareAbsTo(a)>=0)
		{
			subMag(a.dig,a.len);
			//if(len==1 && dig[0]==0) sign = 1;
			return;
		}

		final int[] v = a.dig;
		final int vlen = a.len;
		if(dig.length<vlen) realloc(vlen+1);

		sign = -sign;
		long dif = 0; int i = 0;
		for(; i<len; i++)
		{
			dif = (v[i]&mask)-(dig[i]&mask) + dif;
			dig[i] = (int)dif;
			dif >>= 32;
		}
		if(vlen>len){ System.arraycopy(v, len, dig, len, vlen-len); len = vlen; }
		if(dif!=0)
		{
			for(; i<vlen && dig[i]==0; i++) --dig[i];
			if(--dig[i]==0 && i+1==len) --len;
		}
		//if(i==vlen) should be impossible
	}


	public void mul(final BigInt mul)
	{
		if(isZero()) return;
		else if(mul.isZero()) setToZero();
		else if(mul.len<=2 || len<=2)
		{
			sign *= mul.sign;
			if(mul.len==1) umul(mul.dig[0]);
			else if(len==1){ final int tmp = dig[0]; assign(mul.dig, mul.len); umul(tmp); }
			else if(mul.len==2) umul(mul.dig[1]<<32|(mul.dig[0]&mask));
			else{ final long tmp = dig[1]<<32|(dig[0]&mask); assign(mul.dig, mul.len); umul(tmp); }
		}
		else if(len<128 || mul.len<128 || (long)len*mul.len<1_000_000) smallMul(mul); //Remove overhead?
		else if(Math.max(len,mul.len)<20000) karatsuba(mul,false); //Tune thresholds and remove hardcode.
		else karatsuba(mul,true);
	}

	public void smallMul(final BigInt mul)
	{
		if(isZero()) return; //Remove?
		if(mul.isZero()){ setToZero(); return; }

		sign *= mul.sign;

		int ulen = len, vlen = mul.len;
		int[] u = dig, v = mul.dig; //ulen <= vlen
		if(vlen<ulen){ u = v; v = dig; ulen = vlen; vlen = len; }

		final int[] res = naiveMul(u,ulen,v,vlen); //Todo remove function overhead.

		dig = res; len = res.length;
		if(res[len-1]==0) --len;
	}

	public void karatsuba(final BigInt mul) //Fix naming?
	{
		karatsuba(mul,false);
	}

	public void karatsuba(final BigInt mul, final boolean parallel) //Not fully tested on small numbers... Fix naming?
	{
		if(mul.dig.length<len) mul.realloc(len);
		else if(dig.length<mul.len) realloc(mul.len);

		if(mul.len<len) for(int i = mul.len; i<len; i++) mul.dig[i] = 0;
		if(len<mul.len) for(int i = len; i<mul.len; i++) dig[i] = 0;

		final int mlen = Math.max(len,mul.len);
		int[] res = null;
		if(!parallel) res = kmul(dig,mul.dig,0,mlen);
		else
		{
			final ExecutorService pool = Executors.newFixedThreadPool(12);
			try{ res = pmul(dig, mul.dig, 0, mlen, 1, pool); }
			catch(Exception e){ System.err.println(e); }
			pool.shutdown();
		}

		len = len+mul.len;
		while(res[len-1]==0) --len;
		dig = res;
		sign *= mul.sign;
	}


	private static int[] naiveMul(final int[] u, final int ulen, final int[] v, final int vlen)
	{
		final int[] res = new int[ulen+vlen];
		long carry = 0, tmp, ui = u[0]&mask;
		for(int j = 0; j<vlen; j++)
		{
			tmp = ui*(v[j]&mask) + carry;
			res[j] = (int)tmp;
			carry = tmp >>> 32;
		}
		res[vlen] = (int)carry;
		for(int i = 1; i<ulen; i++)
		{
			ui = u[i]&mask; carry = 0;
			for(int j = 0; j<vlen; j++)
			{
				tmp = ui*(v[j]&mask) + (res[i+j]&mask) + carry;
				res[i+j] = (int)tmp;
				carry = tmp >>> 32;
			}
			res[i+vlen] = (int)carry;
		}
		return res;
	}

	private static int[] kmul(final int[] x, final int[] y, final int off, final int n)
	{
		// x = x1*B^m + x0
		// y = y1*B^m + y0
		// xy = z2*B^2m + z1*B^m + z0
		// z2 = x1*y1, z0 = x0*y0, z1 = (x1+x0)(y1+y0)-z2-z0
		if(n<=32) //Basecase
		{
			final int[] z = new int[2*n];
			long carry = 0, tmp, xi = x[off]&mask;
			for(int j = 0; j<n; j++)
			{
				tmp = xi*(y[off+j]&mask) + carry;
				z[j] = (int)tmp;
				carry = tmp >>> 32;
			}
			z[n] = (int)carry;
			for(int i = 1; i<n; i++)
			{
				xi = x[off+i]&mask; carry = 0;
				for(int j = 0; j<n; j++)
				{
					tmp = xi*(y[off+j]&mask) + (z[i+j]&mask) + carry;
					z[i+j] = (int)tmp;
					carry = tmp >>> 32;
				}
				z[i+n] = (int)carry;
			}
			return z;
		}

		final int b = n>>>1;
		final int[] z2 = kmul(x,y,off+b,n-b);
		final int[] z0 = kmul(x,y,off,b);

		final int[] x2 = new int[n-b+1], y2 = new int[n-b+1];
		long carry = 0;
		for(int i = 0; i<b; i++)
		{
			carry = (x[off+b+i]&mask) + (x[off+i]&mask) + carry;
			x2[i] = (int)carry;
			carry >>>= 32;
		}
		if((n&1)!=0) x2[b] = x[off+b+b];
		if(carry!=0) if(++x2[b]==0) ++x2[b+1];
		carry = 0;
		for(int i = 0; i<b; i++)
		{
			carry = (y[off+b+i]&mask) + (y[off+i]&mask) + carry;
			y2[i] = (int)carry;
			carry >>>= 32;
		}
		if((n&1)!=0) y2[b] = y[off+b+b];
		if(carry!=0) if(++y2[b]==0) ++y2[b+1];

		final int[] z1 = kmul(x2,y2,0,n-b+(x2[n-b]!=0 || y2[n-b]!=0 ? 1 : 0));

		final int[] z = new int[2*n];
		System.arraycopy(z0,0,z,0,2*b); //Add z0
		System.arraycopy(z2,0,z,b+b,2*(n-b)); //Add z2

		//Add z1
		carry = 0;
		int i = 0;
		for(; i<2*b; i++)
		{
			carry = (z[i+b]&mask) + (z1[i]&mask) - (z2[i]&mask) - (z0[i]&mask) + carry;
			z[i+b] = (int)carry;
			carry >>= 32;
		}
		for(; i<2*(n-b); i++)
		{
			carry = (z[i+b]&mask) + (z1[i]&mask) - (z2[i]&mask) + carry;
			z[i+b] = (int)carry;
			carry >>= 32;
		}
		for(; i<z1.length; i++)
		{
			carry = (z[i+b]&mask) + (z1[i]&mask) + carry;
			z[i+b] = (int)carry;
			carry >>= 32;
		}
		if(carry!=0) while(++z[i+b]==0) ++i;

		return z;
	}

	private static int[] pmul(final int[] x, final int[] y, final int off, final int n, final int lim, final ExecutorService pool) throws Exception
	{
		final int b = n>>>1;

		final Future<int[]> left = pool.submit(new Callable<int[]>(){
			public int[] call() throws Exception
			{
				return lim==0 ? kmul(x,y,off,b) : pmul(x,y,off,b,lim-1,pool);
			}
		});

		final Future<int[]> right = pool.submit(new Callable<int[]>(){
			public int[] call() throws Exception
			{
				return lim==0 ? kmul(x,y,off+b,n-b) : pmul(x,y,off+b,n-b,lim-1,pool);
			}
		});

		final int[] x2 = new int[n-b+1], y2 = new int[n-b+1];
		long carry = 0;
		for(int i = 0; i<b; i++)
		{
			carry = (x[off+b+i]&mask) + (x[off+i]&mask) + carry;
			x2[i] = (int)carry;
			carry >>>= 32;
		}
		if((n&1)!=0) x2[b] = x[off+b+b];
		if(carry!=0) if(++x2[b]==0) ++x2[b+1];
		carry = 0;
		for(int i = 0; i<b; i++)
		{
			carry = (y[off+b+i]&mask) + (y[off+i]&mask) + carry;
			y2[i] = (int)carry;
			carry >>>= 32;
		}
		if((n&1)!=0) y2[b] = y[off+b+b];
		if(carry!=0) if(++y2[b]==0) ++y2[b+1];

		final Future<int[]> mid = pool.submit(new Callable<int[]>(){
			public int[] call() throws Exception
			{
				return lim==0 ? kmul(x2,y2,0,n-b+(x2[n-b]!=0 || y2[n-b]!=0 ? 1 : 0)) :
					pmul(x2,y2,0,n-b+(x2[n-b]!=0 || y2[n-b]!=0 ? 1 : 0),lim-1,pool);
			}
		});

		final int[] z = new int[2*n];

		final int[] z0 = left.get();
		System.arraycopy(z0,0,z,0,2*b);
		final int[] z2 = right.get();
		System.arraycopy(z2,0,z,b+b,2*(n-b));

		final int[] z1 = mid.get();

		carry = 0;
		int i = 0;
		for(; i<2*b; i++)
		{
			carry = (z[i+b]&mask) + (z1[i]&mask) - (z2[i]&mask) - (z0[i]&mask) + carry;
			z[i+b] = (int)carry;
			carry >>= 32;
		}
		for(; i<2*(n-b); i++)
		{
			carry = (z[i+b]&mask) + (z1[i]&mask) - (z2[i]&mask) + carry;
			z[i+b] = (int)carry;
			carry >>= 32;
		}
		for(; i<z1.length; i++)
		{
			carry = (z[i+b]&mask) + (z1[i]&mask) + carry;
			z[i+b] = (int)carry;
			carry >>= 32;
		}
		if(carry!=0) while(++z[i+b]==0) ++i;
		return z;
	}

	public void div(final BigInt div)
	{
		if(div.len==1){ sign *= div.sign; udiv(div.dig[0]); return; }

		int tmp = compareAbsTo(div);
		if(tmp<0){ setToZero(); return; }
		if(tmp==0){ uassign(1, sign*div.sign); return; }

		final int[] q = new int[len-div.len+1];
		if(len==dig.length) realloc(len+1); //We need an extra slot.
		div(dig, div.dig, len, div.len, q);

		dig = q;
		for(len = q.length; len>1 && dig[len-1]==0; --len);
		sign *= div.sign;
	}

	public void rem(final BigInt div)
	{
		// -7/-3 = 2, 2*-3 + -1
		// -7/3 = -2, -2*3 + -1
		// 7/-3 = -2, -2*-3 + 1
		// 7/3 = 2, 2*3 + 1
		if(div.len==1){ urem(div.dig[0]); return; }

		int tmp = compareAbsTo(div);
		if(tmp<0) return;
		if(tmp==0){ setToZero(); return; }

		final int[] q = new int[len-div.len+1];
		if(len==dig.length) realloc(len+1); //We need an extra slot.
		div(dig, div.dig, len, div.len, q);

		for(len = div.len; dig[len-1]==0; --len);
	}

	public BigInt divRem(final BigInt div)
	{
		int tmp = sign;
		if(div.len==1){ sign *= div.sign; return new BigInt(tmp, udiv(div.dig[0])); }

		tmp = compareAbsTo(div);
		if(tmp<0)
		{
			final BigInt cpy = new BigInt(sign,dig,len);
			dig = new int[2]; len = 1; //setToZero()
			return cpy;
		}
		if(tmp==0){ uassign(1, sign *= div.sign); return new BigInt(1,0); }

		final int[] q = new int[len-div.len+1];
		if(len==dig.length) realloc(len+1); //We need an extra slot.
		div(dig, div.dig, len, div.len, q);

		final int[] r = dig;
		dig = q;
		for(len = q.length; len>1 && dig[len-1]==0; --len);

		tmp = div.len;
		while(tmp>1 && r[tmp-1]==0) --tmp;
		sign *= div.sign;
		return new BigInt(sign/div.sign, r, tmp);
	}

	private void div(final int[] u, final int[] v, final int m, final int n, final int[] q)
	{//Hacker'sDelight's implementation of Knuth's Algorithm D
	   final long b = 1L<<32;	 // Number base (32 bits).
	   long qhat;				// Estimated quotient digit.
	   long rhat;	            // A remainder.
	   long p; 	              // Product of two digits.

	   int s, i, j;
	   long t, k;

	   // Normalize by shifting v left just enough so that
	   // its high-order bit is on, and shift u left the
	   // same amount.  We may have to append a high-order
	   // digit on the dividend; we do that unconditionally.

	   s = Integer.numberOfLeadingZeros(v[n-1]);
	   if(s>0) //In Java (x<<32)==(x<<0) so...
	   {
		   for(i = n-1; i > 0; i--) v[i] = (v[i] << s) | (v[i-1] >>> 32-s);
		   v[0] = v[0] << s;

		   u[m] = u[m-1] >>> 32-s;
		   for (i = m-1; i > 0; i--) u[i] = (u[i] << s) | (u[i-1] >>> 32-s);
		   u[0] = u[0] << s;
	   }

	   final long dh = v[n-1]&mask, dl = v[n-2]&mask, hbit = Long.MIN_VALUE;

	   for(j = m-n; j>=0; j--) //Main loop
	   {
	      // Compute estimate qhat of q[j].
	      k = u[j+n]*b + (u[j+n-1]&mask); //Unsigned division is a pain in the ass! ='(
	      qhat = (k >>> 1)/dh << 1;
		  t = k - qhat*dh;
		  if(t + hbit > dh + hbit) qhat++; // qhat = (u[j+n]*b + u[j+n-1])/v[n-1];
	      rhat = k - qhat*dh;

	      while(qhat+hbit >= b+hbit || qhat*dl+hbit > b*rhat+(u[j+n-2]&mask)+hbit) //Unsigned comparison.
	      {
			  qhat = qhat - 1;
			  rhat = rhat + dh;
			  if(rhat+hbit >= b+hbit) break;
	      }

	      // Multiply and subtract.
	      k = 0;
	      for(i = 0; i<n; i++)
	      {
	         p = qhat*(v[i]&mask);
	         t = (u[i+j]&mask) - k - (p&mask);
	         u[i+j] = (int)t;
	         k = (p>>>32) - (t>>32);
	      }
	      t = (u[j+n]&mask) - k;
	      u[j+n] = (int)t;

	      q[j] = (int)qhat;              // Store quotient digit. If we subtracted too much, add back.
	      if(t<0)
	      {
	         q[j] = q[j] - 1;
	         k = 0;
	         for(i = 0; i<n; i++)
	         {
	            t = (u[i+j]&mask) + (v[i]&mask) + k;
	            u[i+j] = (int)t;
	            k = t >>> 32; //>>
	         }
	         u[j+n] += (int)k;
	      }
	   }

	   if(s>0)
	   {
		   //Unnormalize v[].
		   for(i = 0; i<n-1; i++) v[i] = v[i]>>>s | v[i+1]<<32-s;
		   v[n-1] >>>= s;

		   //Unnormalize u[].
		   for(i = 0; i<m; i++) u[i] = u[i]>>>s | u[i+1]<<32-s;
		   u[m] >>>= s;
	   }
	}

	public String toString()
	{
		if(isZero()) return "0";

		int top = len*10;
		final char[] buf = new char[top];
		Arrays.fill(buf, '0');
		final int[] cpy = Arrays.copyOf(dig,len);
		while(true)
		{
			final int j = top;
			for(int tmp = udiv(1_000_000_000); tmp>0; tmp/=10)
				buf[--top] += tmp%10; //TODO: Optimize.
			if(len==1 && dig[0]==0) break;
			else top = j-9;
		}
		if(sign<0) buf[--top] = '-';
		System.arraycopy(cpy,0,dig,0, len = cpy.length);
		return new String(buf, top, buf.length-top);
	}
}
class FasterScanner {
    private InputStream mIs;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public FasterScanner() {
        this(System.in);
    }

    public FasterScanner(InputStream is) {
        mIs = is;
    }

    public int read() {
    if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = mIs.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public String nextLine() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isEndOfLine(c));
        return res.toString();
    }

    public String nextString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
            throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public boolean isEndOfLine(int c) {
        return c == '\n' || c == '\r' || c == -1;
    }

}

