package org.jsoup.select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.TokenQueue;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes2.dex */
public class QueryParser {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final String[] AttributeEvals;
    private static final Pattern NTH_AB;
    private static final Pattern NTH_B;
    private static final String[] combinators;
    private final List<Evaluator> evals = new ArrayList();
    private final String query;

    /* JADX INFO: renamed from: tq */
    private final TokenQueue f5074tq;

    static {
        $assertionsDisabled = !QueryParser.class.desiredAssertionStatus();
        combinators = new String[]{",", ">", Marker.ANY_NON_NULL_MARKER, "~", " "};
        AttributeEvals = new String[]{"=", "!=", "^=", "$=", "*=", "~="};
        NTH_AB = Pattern.compile("(([+-])?(\\d+)?)n(\\s*([+-])?\\s*\\d+)?", 2);
        NTH_B = Pattern.compile("([+-])?(\\d+)");
    }

    private QueryParser(String query) {
        Validate.notEmpty(query);
        String query2 = query.trim();
        this.query = query2;
        this.f5074tq = new TokenQueue(query2);
    }

    public static Evaluator parse(String query) {
        try {
            QueryParser p = new QueryParser(query);
            return p.parse();
        } catch (IllegalArgumentException e) {
            throw new Selector.SelectorParseException(e.getMessage(), new Object[0]);
        }
    }

    Evaluator parse() {
        this.f5074tq.consumeWhitespace();
        if (this.f5074tq.matchesAny(combinators)) {
            this.evals.add(new StructuralEvaluator.Root());
            combinator(this.f5074tq.consume());
        } else {
            findElements();
        }
        while (!this.f5074tq.isEmpty()) {
            boolean seenWhite = this.f5074tq.consumeWhitespace();
            if (this.f5074tq.matchesAny(combinators)) {
                combinator(this.f5074tq.consume());
            } else if (seenWhite) {
                combinator(' ');
            } else {
                findElements();
            }
        }
        return this.evals.size() == 1 ? this.evals.get(0) : new CombiningEvaluator.And(this.evals);
    }

    private void combinator(char combinator) {
        Evaluator currentEval;
        Evaluator rootEval;
        CombiningEvaluator.C2197Or or;
        Evaluator currentEval2;
        this.f5074tq.consumeWhitespace();
        String subQuery = consumeSubQuery();
        Evaluator newEval = parse(subQuery);
        boolean replaceRightMost = false;
        if (this.evals.size() == 1) {
            currentEval = this.evals.get(0);
            rootEval = currentEval;
            if ((rootEval instanceof CombiningEvaluator.C2197Or) && combinator != ',') {
                currentEval = ((CombiningEvaluator.C2197Or) currentEval).rightMostEvaluator();
                if (!$assertionsDisabled && currentEval == null) {
                    throw new AssertionError();
                }
                replaceRightMost = true;
            }
        } else {
            currentEval = new CombiningEvaluator.And(this.evals);
            rootEval = currentEval;
        }
        this.evals.clear();
        switch (combinator) {
            case ' ':
                currentEval2 = new CombiningEvaluator.And(new StructuralEvaluator.Parent(currentEval), newEval);
                break;
            case '+':
                currentEval2 = new CombiningEvaluator.And(new StructuralEvaluator.ImmediatePreviousSibling(currentEval), newEval);
                break;
            case ',':
                if (currentEval instanceof CombiningEvaluator.C2197Or) {
                    or = (CombiningEvaluator.C2197Or) currentEval;
                } else {
                    or = new CombiningEvaluator.C2197Or();
                    or.add(currentEval);
                }
                or.add(newEval);
                currentEval2 = or;
                break;
            case '>':
                currentEval2 = new CombiningEvaluator.And(new StructuralEvaluator.ImmediateParent(currentEval), newEval);
                break;
            case '~':
                currentEval2 = new CombiningEvaluator.And(new StructuralEvaluator.PreviousSibling(currentEval), newEval);
                break;
            default:
                throw new Selector.SelectorParseException("Unknown combinator: " + combinator, new Object[0]);
        }
        if (replaceRightMost) {
            ((CombiningEvaluator.C2197Or) rootEval).replaceRightMostEvaluator(currentEval2);
        } else {
            rootEval = currentEval2;
        }
        this.evals.add(rootEval);
    }

    private String consumeSubQuery() {
        StringBuilder sq = StringUtil.borrowBuilder();
        while (!this.f5074tq.isEmpty()) {
            if (this.f5074tq.matches("(")) {
                sq.append("(").append(this.f5074tq.chompBalanced('(', ')')).append(")");
            } else if (this.f5074tq.matches("[")) {
                sq.append("[").append(this.f5074tq.chompBalanced('[', ']')).append("]");
            } else if (this.f5074tq.matchesAny(combinators)) {
                if (sq.length() > 0) {
                    break;
                }
                this.f5074tq.consume();
            } else {
                sq.append(this.f5074tq.consume());
            }
        }
        return StringUtil.releaseBuilder(sq);
    }

    private void findElements() {
        if (this.f5074tq.matchChomp("#")) {
            byId();
            return;
        }
        if (this.f5074tq.matchChomp(".")) {
            byClass();
            return;
        }
        if (this.f5074tq.matchesWord() || this.f5074tq.matches("*|")) {
            byTag();
            return;
        }
        if (this.f5074tq.matches("[")) {
            byAttribute();
            return;
        }
        if (this.f5074tq.matchChomp(Marker.ANY_MARKER)) {
            allElements();
            return;
        }
        if (this.f5074tq.matchChomp(":lt(")) {
            indexLessThan();
            return;
        }
        if (this.f5074tq.matchChomp(":gt(")) {
            indexGreaterThan();
            return;
        }
        if (this.f5074tq.matchChomp(":eq(")) {
            indexEquals();
            return;
        }
        if (this.f5074tq.matches(":has(")) {
            has();
            return;
        }
        if (this.f5074tq.matches(":contains(")) {
            contains(false);
            return;
        }
        if (this.f5074tq.matches(":containsOwn(")) {
            contains(true);
            return;
        }
        if (this.f5074tq.matches(":containsData(")) {
            containsData();
            return;
        }
        if (this.f5074tq.matches(":matches(")) {
            matches(false);
            return;
        }
        if (this.f5074tq.matches(":matchesOwn(")) {
            matches(true);
            return;
        }
        if (this.f5074tq.matches(":not(")) {
            not();
            return;
        }
        if (this.f5074tq.matchChomp(":nth-child(")) {
            cssNthChild(false, false);
            return;
        }
        if (this.f5074tq.matchChomp(":nth-last-child(")) {
            cssNthChild(true, false);
            return;
        }
        if (this.f5074tq.matchChomp(":nth-of-type(")) {
            cssNthChild(false, true);
            return;
        }
        if (this.f5074tq.matchChomp(":nth-last-of-type(")) {
            cssNthChild(true, true);
            return;
        }
        if (this.f5074tq.matchChomp(":first-child")) {
            this.evals.add(new Evaluator.IsFirstChild());
            return;
        }
        if (this.f5074tq.matchChomp(":last-child")) {
            this.evals.add(new Evaluator.IsLastChild());
            return;
        }
        if (this.f5074tq.matchChomp(":first-of-type")) {
            this.evals.add(new Evaluator.IsFirstOfType());
            return;
        }
        if (this.f5074tq.matchChomp(":last-of-type")) {
            this.evals.add(new Evaluator.IsLastOfType());
            return;
        }
        if (this.f5074tq.matchChomp(":only-child")) {
            this.evals.add(new Evaluator.IsOnlyChild());
            return;
        }
        if (this.f5074tq.matchChomp(":only-of-type")) {
            this.evals.add(new Evaluator.IsOnlyOfType());
            return;
        }
        if (this.f5074tq.matchChomp(":empty")) {
            this.evals.add(new Evaluator.IsEmpty());
        } else if (this.f5074tq.matchChomp(":root")) {
            this.evals.add(new Evaluator.IsRoot());
        } else {
            if (this.f5074tq.matchChomp(":matchText")) {
                this.evals.add(new Evaluator.MatchText());
                return;
            }
            throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.query, this.f5074tq.remainder());
        }
    }

    private void byId() {
        String id = this.f5074tq.consumeCssIdentifier();
        Validate.notEmpty(id);
        this.evals.add(new Evaluator.C2198Id(id));
    }

    private void byClass() {
        String className = this.f5074tq.consumeCssIdentifier();
        Validate.notEmpty(className);
        this.evals.add(new Evaluator.Class(className.trim()));
    }

    private void byTag() {
        String tagName = Normalizer.normalize(this.f5074tq.consumeElementSelector());
        Validate.notEmpty(tagName);
        if (tagName.startsWith("*|")) {
            String plainTag = tagName.substring(2);
            this.evals.add(new CombiningEvaluator.C2197Or(new Evaluator.Tag(plainTag), new Evaluator.TagEndsWith(tagName.replace("*|", ":"))));
        } else {
            if (tagName.contains("|")) {
                tagName = tagName.replace("|", ":");
            }
            this.evals.add(new Evaluator.Tag(tagName));
        }
    }

    private void byAttribute() {
        TokenQueue cq = new TokenQueue(this.f5074tq.chompBalanced('[', ']'));
        String key = cq.consumeToAny(AttributeEvals);
        Validate.notEmpty(key);
        cq.consumeWhitespace();
        if (cq.isEmpty()) {
            if (key.startsWith("^")) {
                this.evals.add(new Evaluator.AttributeStarting(key.substring(1)));
                return;
            } else {
                this.evals.add(new Evaluator.Attribute(key));
                return;
            }
        }
        if (cq.matchChomp("=")) {
            this.evals.add(new Evaluator.AttributeWithValue(key, cq.remainder()));
            return;
        }
        if (cq.matchChomp("!=")) {
            this.evals.add(new Evaluator.AttributeWithValueNot(key, cq.remainder()));
            return;
        }
        if (cq.matchChomp("^=")) {
            this.evals.add(new Evaluator.AttributeWithValueStarting(key, cq.remainder()));
            return;
        }
        if (cq.matchChomp("$=")) {
            this.evals.add(new Evaluator.AttributeWithValueEnding(key, cq.remainder()));
        } else if (cq.matchChomp("*=")) {
            this.evals.add(new Evaluator.AttributeWithValueContaining(key, cq.remainder()));
        } else {
            if (cq.matchChomp("~=")) {
                this.evals.add(new Evaluator.AttributeWithValueMatching(key, Pattern.compile(cq.remainder())));
                return;
            }
            throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", this.query, cq.remainder());
        }
    }

    private void allElements() {
        this.evals.add(new Evaluator.AllElements());
    }

    private void indexLessThan() {
        this.evals.add(new Evaluator.IndexLessThan(consumeIndex()));
    }

    private void indexGreaterThan() {
        this.evals.add(new Evaluator.IndexGreaterThan(consumeIndex()));
    }

    private void indexEquals() {
        this.evals.add(new Evaluator.IndexEquals(consumeIndex()));
    }

    private void cssNthChild(boolean backwards, boolean ofType) {
        int a;
        int b = 0;
        String argS = Normalizer.normalize(this.f5074tq.chompTo(")"));
        Matcher mAB = NTH_AB.matcher(argS);
        Matcher mB = NTH_B.matcher(argS);
        if ("odd".equals(argS)) {
            a = 2;
            b = 1;
        } else if ("even".equals(argS)) {
            a = 2;
            b = 0;
        } else if (mAB.matches()) {
            a = mAB.group(3) != null ? Integer.parseInt(mAB.group(1).replaceFirst("^\\+", "")) : 1;
            if (mAB.group(4) != null) {
                b = Integer.parseInt(mAB.group(4).replaceFirst("^\\+", ""));
            }
        } else if (mB.matches()) {
            a = 0;
            b = Integer.parseInt(mB.group().replaceFirst("^\\+", ""));
        } else {
            throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", argS);
        }
        if (ofType) {
            if (backwards) {
                this.evals.add(new Evaluator.IsNthLastOfType(a, b));
                return;
            } else {
                this.evals.add(new Evaluator.IsNthOfType(a, b));
                return;
            }
        }
        if (backwards) {
            this.evals.add(new Evaluator.IsNthLastChild(a, b));
        } else {
            this.evals.add(new Evaluator.IsNthChild(a, b));
        }
    }

    private int consumeIndex() {
        String indexS = this.f5074tq.chompTo(")").trim();
        Validate.isTrue(StringUtil.isNumeric(indexS), "Index must be numeric");
        return Integer.parseInt(indexS);
    }

    private void has() {
        this.f5074tq.consume(":has");
        String subQuery = this.f5074tq.chompBalanced('(', ')');
        Validate.notEmpty(subQuery, ":has(el) subselect must not be empty");
        this.evals.add(new StructuralEvaluator.Has(parse(subQuery)));
    }

    private void contains(boolean own) {
        this.f5074tq.consume(own ? ":containsOwn" : ":contains");
        String searchText = TokenQueue.unescape(this.f5074tq.chompBalanced('(', ')'));
        Validate.notEmpty(searchText, ":contains(text) query must not be empty");
        if (own) {
            this.evals.add(new Evaluator.ContainsOwnText(searchText));
        } else {
            this.evals.add(new Evaluator.ContainsText(searchText));
        }
    }

    private void containsData() {
        this.f5074tq.consume(":containsData");
        String searchText = TokenQueue.unescape(this.f5074tq.chompBalanced('(', ')'));
        Validate.notEmpty(searchText, ":containsData(text) query must not be empty");
        this.evals.add(new Evaluator.ContainsData(searchText));
    }

    private void matches(boolean own) {
        this.f5074tq.consume(own ? ":matchesOwn" : ":matches");
        String regex = this.f5074tq.chompBalanced('(', ')');
        Validate.notEmpty(regex, ":matches(regex) query must not be empty");
        if (own) {
            this.evals.add(new Evaluator.MatchesOwn(Pattern.compile(regex)));
        } else {
            this.evals.add(new Evaluator.Matches(Pattern.compile(regex)));
        }
    }

    private void not() {
        this.f5074tq.consume(":not");
        String subQuery = this.f5074tq.chompBalanced('(', ')');
        Validate.notEmpty(subQuery, ":not(selector) subselect must not be empty");
        this.evals.add(new StructuralEvaluator.Not(parse(subQuery)));
    }

    public String toString() {
        return this.query;
    }
}
