/*
 * Copyright 2012 Harald Wellmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.cdi.tck.porting.owb;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.context.AbstractContext;
import org.apache.webbeans.context.ContextFactory;
import org.apache.webbeans.context.RequestContext;
import org.jboss.jsr299.tck.spi.Contexts;

public class ContextsImpl implements Contexts<AbstractContext> {

    public AbstractContext getRequestContext() {
        WebBeansContext webBeansContext = WebBeansContext.getInstance();
        ContextFactory contextFactory = webBeansContext.getContextFactory();

        RequestContext ctx = (RequestContext) contextFactory
            .getStandardContext(RequestScoped.class);

        if (ctx == null) {
            contextFactory.initRequestContext(null);
        }

        return (AbstractContext) contextFactory.getStandardContext(RequestScoped.class);
    }

    public void setActive(AbstractContext context) {
        context.setActive(true);

    }

    public void setInactive(AbstractContext context) {
        context.setActive(false);
    }

    public AbstractContext getDependentContext() {
        WebBeansContext webBeansContext = WebBeansContext.getInstance();
        ContextFactory contextFactory = webBeansContext.getContextFactory();

        return (AbstractContext) contextFactory.getStandardContext(Dependent.class);
    }

    public void destroyContext(AbstractContext context) {
        context.destroy();
    }

}
