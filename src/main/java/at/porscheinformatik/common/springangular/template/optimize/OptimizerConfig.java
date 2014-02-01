package at.porscheinformatik.common.springangular.template.optimize;

import java.util.List;

import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import at.porscheinformatik.common.springangular.resources.ResourceType;

public interface OptimizerConfig
{
	// TODO: hier sollte noch die m�glichkeit rein das man die optimizer
	// sortieren kann. Sowas wie "before:anotheroptimizer"
	void addOptimizer(ResourceType type, String name,
			ResourcePreProcessor processor);

	List<ResourcePreProcessor> getProcessors(ResourceType type);
}