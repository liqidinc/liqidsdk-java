//
// Copyright (c) 2022 Liqid, Inc. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification,
// are not permitted without prior consent.
// In any case where this statement conflicts with a less-restrictive license
// which has been applied by Liqid to this or any containing work
// (such as GPL or MIT), the less restrictive license governs.
//

package com.liqid.sdk;

import java.util.HashMap;
import java.util.LinkedList;

public class StringArrayMap extends HashMap<String, LinkedList<String>> {
    public static class StringArrayMapWrapper extends Wrapper<StringArrayMap> {}
}
