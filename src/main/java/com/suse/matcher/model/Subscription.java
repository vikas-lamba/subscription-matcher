package com.suse.matcher.model;

import com.google.gson.annotations.SerializedName;
import com.suse.matcher.model.System;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.kie.api.definition.type.PropertyReactive;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * An entitlement to use one or more {@link Product}s on one or more
 * {@link System}s.
 */
@PropertyReactive
public class Subscription {

    // JSON fields
    /** The id. */
    public String id;

    /** The type / description. */
    public String type;

    /** The part number */
    @SerializedName("part_number")
    public String partNumber;

    /** The count. */
    @SerializedName("system_limit")
    public Integer systemLimit;

    /** Start Date */
    @SerializedName("starts_at")
    public Date startsAt = new Date(Long.MIN_VALUE);

    /** End Date */
    @SerializedName("expires_at")
    public Date expiresAt = new Date(Long.MAX_VALUE);

    // computed fields
    /** CPUs / socket / IFLs: 0 means instance subscription */
    public Integer cpus;

    /** unlimited virtualization */
    public Boolean unlimitedVirtualization;

    /** stackable */
    public Boolean stackable;

    /** usable for products */
    public List<String> usableProductIds = new LinkedList<String>();

    /** support type */
    public String supportType;

    /** FIXME: needed? lifetime in years */
    public Integer lifetime;

    public Boolean isInstanceSubscription() {
        return (cpus == 0);
    }

    public Boolean matchAnyProductOnSystem(System s) {
        for (String p : s.productIds) {
            if (usableProductIds.contains(p)) {
                return true;
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object objIn) {
        return EqualsBuilder.reflectionEquals(this, objIn);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
