/*
 * Copyright (C) 2016 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.jcore.io;

import java.io.File;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import org.flossware.jcore.AbstractStringifiable;
import org.flossware.jcore.utils.ObjectUtils;
import org.flossware.jcore.utils.StringUtils;

/**
 *
 * This class will monitor a file for changes. If the file does not exist, no changes can be considered. A change to a file is
 * denoted by its last modified time stamp changing.
 *
 * @author sfloess
 *
 */
public class FileMonitor extends AbstractStringifiable {

    /**
     * The file to monitor.
     */
    private final File file;

    /**
     * Last time file was monitored, its date/time.
     */
    private AtomicLong lastModifiedObserved;

    /**
     * Return the timestamp of the last modification of the file being monitor.
     *
     * @return the timestamp of the file last time it was monitored.
     */
    AtomicLong getLastModifiedObserved() {
        return lastModifiedObserved;
    }

    /**
     * This constructor sets the file to monitor.
     *
     * @param file the file to monitor.
     */
    public FileMonitor(final File file) {
        this.file = ObjectUtils.ensureObject(file, "Must have a file to monitor");
        this.lastModifiedObserved = new AtomicLong(file.lastModified());
    }

    /**
     * This constructor sets the file to monitor using a file name.
     *
     * @param fileName the name of the file to monitor.
     */
    public FileMonitor(final String fileName) {
        this(new File(StringUtils.ensureString(fileName, "Cannot have a blank file name!")));
    }

    /**
     * Return the file to monitor.
     *
     * @return the file to monitor.
     */
    public File getFile() {
        return file;
    }

    /**
     * Return true if the file being monitored exists, or false if not... The file could be deleted - in which case it technically
     * changed, but there is nothing to monitor.
     *
     * @return true if the file exists or false if not.
     */
    public boolean exists() {
        return logAndReturn(Level.FINEST, "File exists [{0}] for file [{1}]", getFile().exists(), getFile());
    }

    /**
     * Return true if the file changed or false if not. Be aware, if the file does not exists, its as if the file has not changed.
     * Call exists() to see if it no longer exists.
     *
     * @return true if the file changed or false if not.
     */
    public boolean isChanged() {
        if (!exists()) {
            return false;
        }

        log(Level.FINEST, "File, {0} lastModified on {1} -> {2}", getFile(), getFile().lastModified(), getLastModifiedObserved().get());

        if (getFile().lastModified() == getLastModifiedObserved().get()) {
            return false;
        }

        log(Level.FINEST, "Setting last observed modified for file, {0} lastModified {1}}", getFile(), getFile().lastModified());

        getLastModifiedObserved().set(getFile().lastModified());

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder toStringBuilder(final StringBuilder sb, final String prefix) {
        return sb.append(prefix).append("file [").append(getFile()).append("] - exists [").append(exists()).append("]");
    }
}
