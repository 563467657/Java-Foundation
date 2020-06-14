`@component`的派生性

有很多标注了`@Component`的注解，这些注解都会被`@ComponentScan`扫描到

比如@Service、@Controller、@Repository、@Configuration

处理类为`ConfigurationClassParser`

扫描类为`ClassPathBeanDefinitionScanner`

