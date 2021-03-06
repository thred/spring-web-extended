package at.porscheinformatik.common.spring.web.extended.template.part;

/**
 * @author Daniel Furtlehner
 * 
 */
public class StringPart implements TemplatePart
{
	private String string;

	public StringPart(String string)
	{
		this.string = string;
	}

	@Override
	public String render()
	{
		return string;
	}

	@Override
	public String toString()
	{
		return string;
	}
}
