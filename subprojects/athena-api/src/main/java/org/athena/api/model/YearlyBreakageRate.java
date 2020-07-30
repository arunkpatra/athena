/*
 * MIT License
 *
 * Copyright (c) 2020 Arun Patra
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.athena.api.model;

public class YearlyBreakageRate {

    private final String cartTypeCode;
    private final float breakageRate;
    private final int year;

    public YearlyBreakageRate(String cartTypeCode, float breakageRate, int year) {
        this.cartTypeCode = cartTypeCode;
        this.breakageRate = breakageRate;
        this.year = year;
    }

    public String getCartTypeCode() {
        return cartTypeCode;
    }

    public float getBreakageRate() {
        return breakageRate;
    }

    public int getYear() {
        return year;
    }
}
