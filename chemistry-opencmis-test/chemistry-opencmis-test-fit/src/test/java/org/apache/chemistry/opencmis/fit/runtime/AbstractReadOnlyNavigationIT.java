/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.chemistry.opencmis.fit.runtime;

import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.junit.Test;

public abstract class AbstractReadOnlyNavigationIT extends AbstractSessionTest {

    @Test
    public void navigateChildrenSkip() {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME;
        Folder folder = (Folder) this.session.getObjectByPath(path);
        Assert.assertNotNull("folder not found: " + path, folder);

        ItemIterable<CmisObject> pl = folder.getChildren().skipTo(2).getPage(2);
        Assert.assertNotNull(pl);

        for (CmisObject o : pl) {
            Assert.assertNotNull(o);
        }
    }

    @Test
    public void navigateChildrenMin() {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME;
        Folder folder = (Folder) this.session.getObjectByPath(path);
        Assert.assertNotNull("folder not found: " + path, folder);

        ItemIterable<CmisObject> pl = folder.getChildren();
        Assert.assertNotNull(pl);

        for (CmisObject o : pl) {
            Assert.assertNotNull(o);
        }
    }

    @Test
    public void navigateChildrenMax() {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME;
        Folder folder = (Folder) this.session.getObjectByPath(path);
        Assert.assertNotNull("folder not found: " + path, folder);

        this.session.getDefaultContext().setMaxItemsPerPage(1000);
        ItemIterable<CmisObject> pl = folder.getChildren();
        Assert.assertNotNull(pl);

        for (CmisObject o : pl) {
            Assert.assertNotNull(o);
        }
    }

    @Test
    public void navigateChildrenMed() {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME;
        Folder folder = (Folder) this.session.getObjectByPath(path);
        Assert.assertNotNull("folder not found: " + path, folder);

        this.session.getDefaultContext().setMaxItemsPerPage(2);
        ItemIterable<CmisObject> pl = folder.getChildren();
        Assert.assertNotNull(pl);

        for (CmisObject o : pl) {
            Assert.assertNotNull(o);
        }
    }

    @Test
    public void navigateDescendantsMin() {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME;
        Folder folder = (Folder) this.session.getObjectByPath(path);
        Assert.assertNotNull("folder not found: " + path, folder);

        List<Tree<FileableCmisObject>> desc = folder.getDescendants(1);
        Assert.assertNotNull(desc);
        Assert.assertFalse(desc.isEmpty());

        for (Tree<FileableCmisObject> o : desc) {
            Assert.assertNotNull(o);
            Assert.assertNotNull(o.getItem());
        }
    }

    @Test
    public void navigateDescendantsMax() {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME;
        Folder folder = (Folder) this.session.getObjectByPath(path);
        Assert.assertNotNull("folder not found: " + path, folder);

        List<Tree<FileableCmisObject>> desc = folder.getDescendants(1000);
        Assert.assertNotNull(desc);
        Assert.assertFalse(desc.isEmpty());

        for (Tree<FileableCmisObject> o : desc) {
            Assert.assertNotNull(o);
            Assert.assertNotNull(o.getItem());
        }
    }

    @Test
    public void navigateDescendantsMed() {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME;
        Folder folder = (Folder) this.session.getObjectByPath(path);
        Assert.assertNotNull("folder not found: " + path, folder);

        List<Tree<FileableCmisObject>> desc = folder.getDescendants(2);
        Assert.assertNotNull(desc);
        Assert.assertFalse(desc.isEmpty());

        for (Tree<FileableCmisObject> o : desc) {
            Assert.assertNotNull(o);
            Assert.assertNotNull(o.getItem());
        }
    }

    @Test
    public void navigateTreeMed() {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME;
        Folder folder = (Folder) this.session.getObjectByPath(path);
        Assert.assertNotNull("folder not found: " + path, folder);

        List<Tree<FileableCmisObject>> tree = folder.getFolderTree(2);
        Assert.assertNotNull(tree);
        Assert.assertFalse(tree.isEmpty());

        for (Tree<FileableCmisObject> o : tree) {
            Assert.assertNotNull(o);
            Assert.assertNotNull(o.getItem());
        }
    }

    @Test
    public void navigateTreeMin() {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME;
        Folder folder = (Folder) this.session.getObjectByPath(path);
        Assert.assertNotNull("folder not found: " + path, folder);

        List<Tree<FileableCmisObject>> tree = folder.getFolderTree(1);
        Assert.assertNotNull(tree);
        Assert.assertFalse(tree.isEmpty());

        for (Tree<FileableCmisObject> o : tree) {
            Assert.assertNotNull(o);
            Assert.assertNotNull(o.getItem());
        }
    }

    @Test
    public void navigateTreeMax() {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME;
        Folder folder = (Folder) this.session.getObjectByPath(path);
        Assert.assertNotNull("folder not found: " + path, folder);

        List<Tree<FileableCmisObject>> tree = folder.getFolderTree(1000);
        Assert.assertNotNull(tree);
        Assert.assertFalse(tree.isEmpty());

        for (Tree<FileableCmisObject> o : tree) {
            Assert.assertNotNull(o);
            Assert.assertNotNull(o.getItem());
        }
    }

    @Test
    public void navigatePagingRandom() {
        String path = "/" + Fixture.TEST_ROOT_FOLDER_NAME;
        Folder folder = (Folder) this.session.getObjectByPath(path);
        Assert.assertNotNull("folder not found: " + path, folder);

        this.session.getDefaultContext().setMaxItemsPerPage(2);
        ItemIterable<CmisObject> pl = folder.getChildren();
        Assert.assertNotNull(pl);

        CmisObject firstObject = pl.iterator().next();
        Assert.assertNotNull(firstObject);
    }

    @Test
    public void rootParent() {
        Folder root = session.getRootFolder();
        Assert.assertNotNull(root);
        Assert.assertNotNull(root.getName());
        Assert.assertNotNull(root.getId());
        Assert.assertNotNull(root.getType());
        List<Property<?>> props = root.getProperties();
        Assert.assertNotNull(props);
        Assert.assertTrue(props.size() > 0);
        Assert.assertEquals("/", root.getPath());
        Assert.assertEquals(Collections.singletonList("/"), root.getPaths());
        Assert.assertNull(root.getFolderParent());
        Assert.assertEquals(Collections.emptyList(), root.getParents());
    }
}
