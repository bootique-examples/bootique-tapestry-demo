import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.Bootique;
import io.bootique.tapestry.TapestryModule;
import io.bootique.tapestry.demo.services.AppModule;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.services.LibraryMapping;

public class Application implements Module {

    public static void main(String[] args) {

        Bootique.app(args).autoLoadModules()
                .module(Application.class)
                .exec();
    }

    @Override
    public void configure(Binder binder) {

        TapestryModule.extend(binder)
                .setSymbol(SymbolConstants.SUPPORTED_LOCALES, "en")
                .setSymbol(SymbolConstants.START_PAGE_NAME, "Login")
                .addLibraryMapping(new LibraryMapping("lib", "io.bootique.tapestry.testlib"))
                .addTapestryModule(AppModule.class);
    }
}
