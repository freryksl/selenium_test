import log.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class test_logger implements TestWatcher {
    Logger log = new Logger();
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {}
    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info(context.getDisplayName()+" Successful");
    }
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {}
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.error("Error: "+cause.getMessage());
    }
}