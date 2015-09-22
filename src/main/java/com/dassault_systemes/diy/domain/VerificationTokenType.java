package com.dassault_systemes.diy.domain;

/**
 * Type of verification token.
 *
 * @apiNote Keep the same declaration order because Hibernate use the enum.ordinal value
 */
public enum VerificationTokenType {
    EMAIL_REGISTRATION,
    LOST_PASSWORD,
    EMAIL_VERIFICATION
}
