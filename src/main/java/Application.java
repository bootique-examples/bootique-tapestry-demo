import io.bootique.BaseModule;
import io.bootique.Bootique;
import io.bootique.di.Binder;
import io.bootique.tapestry.TapestryModule;
import io.bootique.tapestry.demo.services.AppModule;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.webresources.modules.WebResourcesModule;

public class Application extends BaseModule {

    public static void main(String[] args) {

        Bootique.app(args).autoLoadModules()
                .module(Application.class)
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {

        TapestryModule.extend(binder)
                .setSymbol(SymbolConstants.SUPPORTED_LOCALES, "en")
                .setSymbol(SymbolConstants.START_PAGE_NAME, "Login")
                //additional root package for the application
                .addLibraryMapping(new LibraryMapping("lib", "io.bootique.tapestry.testlib"))
                .addTapestryModule(AppModule.class)
                .addTapestryModule(WebResourcesModule.class);
    }
}
