package com.arjun.automation.genericLib;

import java.util.Map;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

public class Verifier extends Assertion 
{
	String finalString;

	private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();

	@Override
	protected void doAssert(IAssert<?> a) 
	{
		onBeforeAssert(a);
		try 
		{
			a.doAssert();
			onAssertSuccess(a);
		} 
		catch (AssertionError ex) 
		{
			onAssertFailure(a, ex);
			m_errors.put(ex, a);
		} 
		finally 
		{
			onAfterAssert(a);
		}
	}

	public void assertAll() 
	{
		if (!m_errors.isEmpty()) 
		{
			StringBuilder sb = new StringBuilder("The following asserts failed : ---");
			boolean first = true;
			for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) 
			{
				if (first) 
				{
					first = false;
				} 
				else 
				{
					sb.append(";;");
				}
				sb.append("  ");
				sb.append(ae.getKey().getMessage());
				// sb.append("\n\t");
				// sb.append("\nStack Trace :");
				// sb.append(Arrays.toString(ae.getKey().getStackTrace()).replaceAll(",",
				// "\n"));

				finalString = sb.toString();
			}
			throw new AssertionError(sb.toString());
		}
	}
}